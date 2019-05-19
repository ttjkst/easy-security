package org.easySecurity.server.user;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.LinkedList;

public class MultOAuth2ServerUserService<R extends OAuth2UserRequest, U extends OAuth2User> implements OAuth2UserService<R, OAuth2User> {

    private LinkedList<OAuth2ServerUserService> services;

    public MultOAuth2ServerUserService(LinkedList<OAuth2ServerUserService> services) {
        this.services = services;
    }

    @Override
    public OAuth2User loadUser(R userRequest) throws OAuth2AuthenticationException {
        for (OAuth2ServerUserService service : services) {
            if(service.support(userRequest.getClientRegistration())){
                return service.loadUser(userRequest);
            }
        }
        throw new RuntimeException("can not found a  OAuth2ServerUserService to support client( client id is "+userRequest.getClientRegistration().getClientId()+") .");
    }

    public void addServerUserService(OAuth2ServerUserService service){
        services.add(service);
    }
}
