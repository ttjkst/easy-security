package org.easySecutity.client;

import org.easySecutity.client.user.OAuth2ClientUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;

public abstract class ClientConfigUtils {
    public static OAuth2UserService  getOAuth2UserService(HttpSecurity builder){
        OAuth2UserService sharedObject = builder.getSharedObject(OAuth2UserService.class);
        if(sharedObject==null){
            sharedObject = builder.getSharedObject(ApplicationContext.class).getBean(OAuth2UserService.class);
            if(sharedObject==null){
                sharedObject = new OAuth2ClientUserService();
            }
            builder.setSharedObject(OAuth2UserService.class,sharedObject);
        }
        return sharedObject;
    }
}
