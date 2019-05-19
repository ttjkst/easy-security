package org.easySecurity.server.user;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface OAuth2ServerUserService<R extends OAuth2UserRequest, U extends OAuth2User> extends OAuth2UserService {
    boolean support(ClientRegistration clientRegistration);
}
