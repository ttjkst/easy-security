package org.easySecurity.server.user;

import org.easySecurity.core.user.UserInfo;
import org.easySecurity.server.toClient.ClientInfo;
import org.easySecurity.server.user.github.GithubUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OAuth2UserWithMultOAuth2Info extends UserInfo implements OAuth2User {

    public static final String  GITHUB_USER="githubUser";
    public static final String  GITHUB_USER_ACCESS_CODE="githubUserAccessCode";
    private Map<String,Object> extra;

    private Map<String, ClientInfo> hasAuthorizedClientInfo = new ConcurrentHashMap<>(16);

    public OAuth2UserWithMultOAuth2Info(UserInfo userInfo, Map<String,Object> extra) {
        super(userInfo);
        this.extra = extra==null? Collections.emptyMap():extra;
    }

    public Collection<ClientInfo> getHasAuthorizedClientInfo() {
        return hasAuthorizedClientInfo.values();
    }

    public void removeClinetInfoFromHasAuthorizedClientInfo(ClientDetails clientDetails){
        hasAuthorizedClientInfo.remove(clientDetails.getClientId());
    }

    public void addClinetInfoFromHasAuthorizedClientInfo(ClientInfo clientInfo){
        hasAuthorizedClientInfo.put(clientInfo.getClientId(),clientInfo);
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
