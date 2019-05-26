package org.easySecurity.server.dsl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easySecurity.core.voters.AddVotersToFilterSecurityInterceptorHelper;
import org.easySecurity.core.voters.UserInfoWebExpresssionAuthorityVoter;
import org.easySecurity.server.user.MultOAuth2ServerUserService;
import org.easySecurity.server.user.OAuth2ServerUserService;
import org.easySecurity.server.user.UniqueProvider;
import org.easySecurity.server.user.github.GithubUserService;
import org.easySecurity.server.utils.ServerConfigUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ServerDsl extends AbstractHttpConfigurer<ServerDsl, HttpSecurity> {

    private static final Log log = LogFactory.getLog(ServerDsl.class);


    private BearerTokenResolver bearerTokenResolver;
    private List<AccessDecisionVoter<? extends Object>> severVoters = new ArrayList<>(10);

    private LinkedList<OAuth2ServerUserService> serverUserServices = new LinkedList();

    private boolean enableRoleToScope =false;

    @Override
    public void init(HttpSecurity builder) throws Exception {



        if(enableRoleToScope){
            setTokenEndpointAuthenticationFilter(builder);
        }
        if(severVoters.isEmpty()){
            severVoters.add(new UserInfoWebExpresssionAuthorityVoter());
        }

        determineOAuth2ProviderConfig(builder);

        MultOAuth2ServerUserService multOAuth2ServerUserService = new MultOAuth2ServerUserService(serverUserServices);
        //defalut open formLogin
        builder.formLogin()
//                //defalut open ResourceServer
//                .and()
//                    .oauth2ResourceServer()
                //defalut open oauth2Login
                .and()
                    .oauth2Login().userInfoEndpoint().userService(multOAuth2ServerUserService);
        builder.authorizeRequests().withObjectPostProcessor(new AddVotersToFilterSecurityInterceptorHelper(severVoters)).anyRequest().authenticated();

    }

    public ServerDsl addSerUserService(OAuth2ServerUserService serverUserService){
        Assert.notNull(serverUserService,"serverUserService must not be null");
        serverUserServices.add(serverUserService);
        return this;
    }

    public ServerDsl voter(AccessDecisionVoter<FilterInvocation> voter){
        severVoters.clear();
        severVoters.add(voter);
        return this;
    }

    public ServerDsl voters(List<AccessDecisionVoter<FilterInvocation>> voters){
        severVoters.clear();
        severVoters.addAll(voters);
        return this;
    }

    public ServerDsl addVoters(List<AccessDecisionVoter<FilterInvocation>> voters){
        severVoters.addAll(voters);
        determineDefalutVoterIfNoneAddIt();
        return this;
    }

    public ServerDsl addVoter(AccessDecisionVoter<FilterInvocation> voter){
        severVoters.add(voter);
        determineDefalutVoterIfNoneAddIt();
        return this;
    }

    private void determineDefalutVoterIfNoneAddIt(){
        for (AccessDecisionVoter<?> webCilentVoter : severVoters) {
            if(webCilentVoter instanceof UserInfoWebExpresssionAuthorityVoter){
                return;
            }
        }
        severVoters.add(new UserInfoWebExpresssionAuthorityVoter());
    }

    private void determineOAuth2ProviderConfig(HttpSecurity builder){
        ClientRegistrationRepository repository = ServerConfigUtils.getBean(builder, ClientRegistrationRepository.class);
        if(repository instanceof  Iterable){
            Iterable<ClientRegistration> registrations = (Iterable<ClientRegistration>) repository;
            for (ClientRegistration registration : registrations) {
                if(registration.getClientName().equals("GitHub")) {
                    Optional<OAuth2ServerUserService> first = serverUserServices.stream().filter(x -> x.support(registration)).findFirst();
                    if (!first.isPresent()) {
                        UniqueProvider bean = ServerConfigUtils.getBean(builder, UniqueProvider.class);
                        serverUserServices.add(new GithubUserService(bean));
                    }
                }
            }
        }
    }

    public ServerDsl enableRoleToScope(){
        enableRoleToScope= true;
        return this;
    }

    private void setTokenEndpointAuthenticationFilter(HttpSecurity builder){
        TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter = ServerConfigUtils.getBean(builder, TokenEndpointAuthenticationFilter.class);
        if(tokenEndpointAuthenticationFilter==null){
            log.info("TokenEndpointAuthenticationFilter can not find in appliactionContext and share object, use auto construction");
            tokenEndpointAuthenticationFilter = autoConstruction(builder);
        }
        builder.addFilterAt(tokenEndpointAuthenticationFilter, BasicAuthenticationFilter.class);
    }

    private TokenEndpointAuthenticationFilter autoConstruction(HttpSecurity builder){
        AuthenticationManager manager = ServerConfigUtils.getBean(builder, AuthenticationManager.class);
        OAuth2RequestFactory bean = ServerConfigUtils.getBean(builder, OAuth2RequestFactory.class);
        Assert.notNull(manager,"can not find AuthenticationManager bean");
        Assert.notNull(bean,"can not find OAuth2RequestFactory bean");
        TokenEndpointAuthenticationFilter filter = new TokenEndpointAuthenticationFilter(manager,bean);
        return filter;
    }

    BearerTokenResolver getBearerTokenResolver(HttpSecurity builder) {
        if ( this.bearerTokenResolver == null ) {
            ApplicationContext applicationContext = ServerConfigUtils.getApplicationContext(builder);
            if ( applicationContext.getBeanNamesForType(BearerTokenResolver.class).length > 0 ) {
                this.bearerTokenResolver = applicationContext.getBean(BearerTokenResolver.class);
            } else {
                this.bearerTokenResolver = new DefaultBearerTokenResolver();
            }
        }

        return this.bearerTokenResolver;
    }
}
