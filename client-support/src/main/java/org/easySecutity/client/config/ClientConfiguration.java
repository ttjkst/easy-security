package org.easySecutity.client.config;

import org.easySecutity.client.ClientProperties;
import org.easySecutity.client.endpints.UserInfoEndpoint;
import org.easySecutity.client.securityContext.ChangeUseInfoExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ClientProperties.class})
public class ClientConfiguration {


    private final ClientProperties clientProperties;

    public ClientConfiguration(ClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    @Bean
    public ChangeUseInfoExecutor changeUseInfoExecutor(){
        return  new ChangeUseInfoExecutor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "easy-security.oauth2.client",name = "disableListeningServerUseInfoChange", havingValue = "false", matchIfMissing = true)
    public UserInfoEndpoint UserInfoEndpoint(){
        UserInfoEndpoint userInfoEndpoint = new UserInfoEndpoint();
        userInfoEndpoint.setExecutor(changeUseInfoExecutor());
        return userInfoEndpoint;
    }
}
