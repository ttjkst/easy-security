package org.easySecutity.client.securityContext;

import org.easySecurity.core.user.ClientUserInfoEntity;
import org.easySecurity.core.utils.AuthorityUtils;
import org.easySecutity.client.user.OAuth2UserForUserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationExchange;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ChangeUseInfoExecutor extends SecurityContextRepositoryListenerAdpter {

    private ConcurrentHashMap<String, ClientUserInfoEntity> waitMap = new ConcurrentHashMap<>(15);

    @Override
    public void afterLoadContext(SecurityContext context) {
        Authentication authentication = context.getAuthentication();
        if(authentication instanceof  OAuth2LoginAuthenticationToken&&
                authentication.getPrincipal() instanceof OAuth2UserForUserInfo) {
                OAuth2UserForUserInfo userForUserInfo = (OAuth2UserForUserInfo) authentication.getPrincipal();
                String uniqueId                       = userForUserInfo.getUniqueId();
                if (waitMap.containsKey(userForUserInfo.getUniqueId())) {
                    ClientUserInfoEntity clientUserInfoEntity = waitMap.get(uniqueId);
                    OAuth2LoginAuthenticationToken olderToken = (OAuth2LoginAuthenticationToken) authentication;
                    OAuth2LoginAuthenticationToken newToken   = switchToNewToken(olderToken, clientUserInfoEntity);
                    context.setAuthentication(newToken);
                    //waitMap.remove(userForUserInfo.getUniqueId());
                }
        }
    }


    private OAuth2LoginAuthenticationToken switchToNewToken(OAuth2LoginAuthenticationToken olderToken, ClientUserInfoEntity newUserInfo){
        ClientRegistration clientRegistration             = olderToken.getClientRegistration();
        OAuth2AuthorizationExchange authorizationExchange = olderToken.getAuthorizationExchange();
        OAuth2AccessToken accessToken                     = olderToken.getAccessToken();
        OAuth2RefreshToken refreshToken                   = olderToken.getRefreshToken();
        OAuth2UserForUserInfo newUseInfoToConetxt         = new OAuth2UserForUserInfo(
                                                                    AuthorityUtils.packGrantedAuthoritys(newUserInfo.getAuthorities()),
                                                                    newUserInfo.getUsername(),
                                                                    newUserInfo.getAuthorityEntities(),
                                                                    accessToken.getTokenValue()
                                                                 );

        OAuth2LoginAuthenticationToken newToken           = new OAuth2LoginAuthenticationToken(
                                                                clientRegistration,
                                                                authorizationExchange,
                                                                newUseInfoToConetxt,
                                                                newUseInfoToConetxt.getAuthorities(),
                                                                accessToken,
                                                                refreshToken);
        return newToken;
    }

    public void addChangeToWaitMap(ClientUserInfoEntity clientUserInfoEntity){
        waitMap.put(clientUserInfoEntity.getTokenValue(), clientUserInfoEntity);
    }

    public void addChangeToWaitMap(List<ClientUserInfoEntity> clientUserInfoEntities){
        Map<String, ClientUserInfoEntity> addToWaitMap = clientUserInfoEntities.stream()
                .collect(Collectors.toMap(k -> k.getTokenValue(), Function.identity()));
        waitMap.putAll(addToWaitMap);
    }
}
