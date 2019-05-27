package org.easySecutity.client.dsl;

import org.easySecurity.core.user.DelegatingOAuth2UserServicePlus;
import org.easySecurity.core.voters.AddVotersToFilterSecurityInterceptorHelper;
import org.easySecutity.client.ClientConfigUtils;
import org.easySecutity.client.ClientProperties;
import org.easySecutity.client.securityContext.ChangeUseInfoExecutor;
import org.easySecutity.client.securityContext.SecurityContextRepositoryListener;
import org.easySecutity.client.securityContext.SecurityContextRepositoryWapper;
import org.easySecutity.client.voters.UserInfoWebExpresssionClientAuthorityVoter;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientDsl extends AbstractHttpConfigurer<ClientDsl, HttpSecurity> {

    private List<AccessDecisionVoter<? extends Object>> webCilentVoters = new ArrayList<>(10);

    private List<OAuth2UserService> oAuth2UserServices                  = new ArrayList<>(10);

    private List<SecurityContextRepositoryListener> repositoryListeners;


    @Override
    public void init(HttpSecurity builder) throws Exception {
        if(webCilentVoters.isEmpty()){
            webCilentVoters.add(new UserInfoWebExpresssionClientAuthorityVoter());
        }
        builder.authorizeRequests().withObjectPostProcessor(new AddVotersToFilterSecurityInterceptorHelper(webCilentVoters));
        OAuth2UserService oAuth2UserService = ClientConfigUtils.getOAuth2UserService(builder);
        if(oAuth2UserService instanceof DelegatingOAuth2UserServicePlus){
            ((DelegatingOAuth2UserServicePlus)oAuth2UserService).addALLOAuth2UserService(oAuth2UserServices);
            builder.oauth2Login().userInfoEndpoint().userService(oAuth2UserService);
        }else{
            oAuth2UserServices.add(oAuth2UserService);
            DelegatingOAuth2UserServicePlus<OAuth2UserRequest, OAuth2User> userServicePlus = new DelegatingOAuth2UserServicePlus<>();
            userServicePlus.addALLOAuth2UserService(oAuth2UserServices);
            builder.oauth2Login().userInfoEndpoint().userService(userServicePlus);
        }
        ClientProperties clientProperties         = ClientConfigUtils.getBean(builder, ClientProperties.class);
        if(!clientProperties.isDisableListeningServerUseInfoChange()&&clientProperties!=null){
            ChangeUseInfoExecutor useInfoExecutor = ClientConfigUtils.getBean(builder, ChangeUseInfoExecutor.class);
            if(repositoryListeners==null){
                repositoryListeners = Arrays.asList(useInfoExecutor);
            }else{
                if(!repositoryListeners.contains(useInfoExecutor)){
                    repositoryListeners.add(useInfoExecutor);
                }
            }
            SecurityContextRepository securityContextRepository = builder
                    .getSharedObject(SecurityContextRepository.class);
            if (securityContextRepository == null) {
                securityContextRepository = new SecurityContextRepositoryWapper(new HttpSessionSecurityContextRepository(),repositoryListeners);
            }
            builder.securityContext().securityContextRepository(securityContextRepository);

        }
    }

    public ClientDsl voter(AccessDecisionVoter<FilterInvocation> voter){
        webCilentVoters.clear();
        webCilentVoters.add(voter);
        return this;
    }

    public ClientDsl voters(List<AccessDecisionVoter<FilterInvocation>> voters){
        webCilentVoters.clear();
        webCilentVoters.addAll(voters);
        return this;
    }

    public ClientDsl addVoters(List<AccessDecisionVoter<FilterInvocation>> voters){
        webCilentVoters.addAll(voters);
        determineDefalutVoterIfNoneAddIt();
        return this;
    }

    public ClientDsl addVoter(AccessDecisionVoter<FilterInvocation> voter){
        webCilentVoters.add(voter);
        determineDefalutVoterIfNoneAddIt();
        return this;
    }
    public ClientDsl userService(OAuth2UserService oAuth2UserService){
        Assert.notNull(oAuth2UserService,"load user service must not be null");
        this.getBuilder().setSharedObject(OAuth2UserService.class,oAuth2UserService);
        return  this;
    }

    private void determineDefalutVoterIfNoneAddIt(){
        for (AccessDecisionVoter<?> webCilentVoter : webCilentVoters) {
            if(webCilentVoter instanceof UserInfoWebExpresssionClientAuthorityVoter){
                return;
            }
        }
        webCilentVoters.add(new UserInfoWebExpresssionClientAuthorityVoter());
    }

    /**
     * developing in the future may be delete
     * */
    public ClientDsl homePage(String homePage){
        return this;
    }
    /**
     * developing in the future may be delete
     * */
    public ClientDsl homePage(String homePage,boolean alwaysUse){
        return this;
    }
    /**
     * developing in the future may be delete
     * */
    public ClientDsl successHandler(){
        return this;
    }
    /**
     * developing in the future may be delete
     * */
    public static ClientDsl bulid(){
        return new ClientDsl();
    }
}
