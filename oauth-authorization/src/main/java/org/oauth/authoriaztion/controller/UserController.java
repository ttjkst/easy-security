package org.oauth.authoriaztion.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easySecurity.core.token.TokenStoreUseTokenEnhancer;
import org.easySecurity.core.user.UserInfo;
import org.easySecurity.core.user.UserInfoEntity;
import org.easySecurity.server.toClient.ClientInfo;
import org.easySecurity.server.user.OAuth2UserWithMultOAuth2Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/")
public class UserController {


    private final static Log logger  = LogFactory.getLog(UserController.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenStoreUseTokenEnhancer tokenStoreUseTokenEnhancer;

    @Autowired
    private ClientDetailsService clientDetailsService;


    @RequestMapping(value="/info/detail",method =RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthorty('SCOPE_userInfo')")
    public UserInfoEntity queryUserInfoByName(){
        Map<String,Object> map = new HashMap<>(1);
        UserDetails userDetails;
        String clientId;
        String accessCode;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof BearerTokenAuthenticationToken){
            BearerTokenAuthenticationToken auth2Token = (BearerTokenAuthenticationToken) authentication;
            try {
                userDetails = tokenStoreUseTokenEnhancer.getStoreAuthentication(auth2Token.getToken());
                clientId = auth2Token.getToken();
                accessCode = auth2Token.getToken();
            } catch (Exception e) {
                logger.error("get userInfo error",e);
                throw new UnsupportedOperationException("unable to load useinfo ",e);
            }
        }else if(authentication instanceof OAuth2Authentication){
            OAuth2Authentication auth2Token = (OAuth2Authentication) authentication;
            userDetails = (UserDetails) auth2Token.getUserAuthentication().getPrincipal();
            clientId    = auth2Token.getOAuth2Request().getClientId();
            accessCode  = auth2Token.getOAuth2Request().getRequestParameters().get("accessCode");
        }else {
            throw new UnsupportedOperationException("unable to load useinfo");
        }
        OAuth2UserWithMultOAuth2Info multOAuth2Info = (OAuth2UserWithMultOAuth2Info) userDetails;
        ClientDetails clientDetail  = clientDetailsService.loadClientByClientId(clientId);
        ClientInfo clientInfo       = new ClientInfo(clientDetail,accessCode);
        multOAuth2Info.addClinetInfoFromHasAuthorizedClientInfo(clientInfo);
        UserInfoEntity userInfoEntity = extractOtherUserInfo(multOAuth2Info);
        return userInfoEntity;
    }

    private UserInfoEntity extractOtherUserInfo(UserInfo userInfo) {
        Collection<? extends GrantedAuthority> grantedAuthorities = userInfo.getAuthorities();
        Set<String> authorityStrs = grantedAuthorities
                .stream().map(x -> ((GrantedAuthority) x).getAuthority())
                .collect(Collectors.toSet());
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUsername(userInfo.getUsername());
        userInfoEntity.setAuthorities(authorityStrs);
        userInfoEntity.setAuthorityEntities(new HashSet<>(userInfo.getBelongToRequestMap().values()));
        return userInfoEntity;
    }

}
