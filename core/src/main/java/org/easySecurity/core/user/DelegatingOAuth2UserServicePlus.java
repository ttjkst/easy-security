package org.easySecurity.core.user;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class DelegatingOAuth2UserServicePlus<R extends OAuth2UserRequest, U extends OAuth2User> implements OAuth2UserService<R, OAuth2User> {

    private final Log log = LogFactory.getLog(DelegatingOAuth2UserServicePlus.class);
    private LinkedList<OAuth2UserService> services = new LinkedList<>();


    public void addOAuth2UserService(OAuth2UserService oAuth2UserService){
        Assert.notNull(oAuth2UserService,"OAuth2UserService must not be null");
        this.services.add(oAuth2UserService);
    }

    public void addALLOAuth2UserService(List<OAuth2UserService> services){
        for (OAuth2UserService service : services) {
            addOAuth2UserService(service);
        }
    }

    @Override
    public OAuth2User loadUser(R userRequest) throws OAuth2AuthenticationException {
        List<OAuth2AuthenticationException> exceptions = new ArrayList<>(services.size());
        for (OAuth2UserService service : services) {
            try {
                OAuth2User oAuth2User = service.loadUser(userRequest);
                if(oAuth2User==null){
                    continue;
                }
                return oAuth2User;
            }catch (Exception e){
                if(log.isDebugEnabled()){
                    log.debug("load user from service error, this load class is "+service.getClass(),e);
                }
                if(e instanceof OAuth2AuthenticationException){
                    exceptions.add((OAuth2AuthenticationException)e);
                }
                continue;
            }
        }
        if (exceptions.isEmpty()) {
            return null;
        } else {
            throw  exceptions.get(0);
        }
    }
}
