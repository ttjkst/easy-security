package org.easySecurity.server.config;


import org.easySecurity.server.clientRegistration.EasyMangerClientRegistrationRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.client.ClientsConfiguredCondition;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientPropertiesRegistrationAdapter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableConfigurationProperties(OAuth2ClientProperties.class)
@Conditional(ClientsConfiguredCondition.class)
public class EasySecurityOAuth2ClientRegistrationRepositoryConfiguration {

    private final OAuth2ClientProperties properties;

    EasySecurityOAuth2ClientRegistrationRepositoryConfiguration(OAuth2ClientProperties properties) {
        this.properties = properties;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(ClientRegistrationRepository.class)
    @ConditionalOnProperty(prefix = "easySpring.oauth2.clientRegistrationRepository", name = "enabled", havingValue = "true", matchIfMissing = true)
    public EasyMangerClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = new ArrayList<>(
                OAuth2ClientPropertiesRegistrationAdapter
                        .getClientRegistrations(this.properties).values());
        EasyMangerClientRegistrationRepository repository = new EasyMangerClientRegistrationRepository();
        repository.addAll(registrations);
        return repository;
    }

}
