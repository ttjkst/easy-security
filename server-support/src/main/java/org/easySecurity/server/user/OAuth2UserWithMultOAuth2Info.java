package org.easySecurity.server.user;

import org.easySecurity.core.user.UserInfo;
import org.easySecurity.server.user.github.GithubUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OAuth2UserWithMultOAuth2Info extends UserInfo implements OAuth2User {

    public static final String  GITHUB_USER="githubUser";
    public static final String  GITHUB_USER_ACCESS_CODE="githubUserAccessCode";
    private Map<String,Object> extra;

    private Map<String, ClientRegistration> hasAuthorizedClientInfo = new ConcurrentHashMap<>(16);

    public OAuth2UserWithMultOAuth2Info(UserInfo userInfo, Map<String,Object> extra) {
        super(userInfo);
        this.extra = extra==null? Collections.emptyMap():extra;
    }

    public Map<String, ClientRegistration> clientInfoMap() {
        return hasAuthorizedClientInfo;
    }

    public void putClientInfoToAuthorizedMap(ClientRegistration clientInfo){
        hasAuthorizedClientInfo.put(clientInfo.getClientName(),clientInfo);
    }

    public void removeClientInfoFromAuthorizedMap(ClientRegistration clientInfo){
        hasAuthorizedClientInfo.remove(clientInfo.getClientName());
    }

    public GithubUser getGithubUser(){
        Object githubUser = extra.get(GITHUB_USER);
        if(githubUser==null){
            return null;
        }
        else {
            return (GithubUser)githubUser;
        }
    }

    public String getGithubUserAccessCode(){
        Object githubUser = extra.get(GITHUB_USER_ACCESS_CODE);
        if(githubUser==null){
            return null;
        }
        else {
            return (String) githubUser;
        }
    }

    @Override
    public Map<String, Object> getAttributes() {
        return extra;
    }

    @Override
    public String getName() {
        return getUsername();
    }
}
