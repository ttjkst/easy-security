package org.oauth.authoriaztion.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easySecurity.core.token.TokenStoreUseTokenEnhancer;
import org.easySecurity.core.user.UserInfo;
import org.easySecurity.core.user.UserInfoEnity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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


    @RequestMapping(value="/info/detail",method =RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasAuthorty('SCOPE_userInfo')")
    public UserInfoEnity queryUserInfoByName(){
        Map<String,Object> map = new HashMap<>(1);
        UserDetails userDetails;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof BearerTokenAuthenticationToken){
            BearerTokenAuthenticationToken auth2Token = (BearerTokenAuthenticationToken) authentication;
            try {
                userDetails = tokenStoreUseTokenEnhancer.getStoreAuthentication(auth2Token.getToken());
            } catch (Exception e) {
                logger.error("get userInfo error",e);
                throw new UnsupportedOperationException("unable to load useinfo ",e);
            }
        }else if(authentication instanceof OAuth2Authentication){
            OAuth2Authentication auth2Token = (OAuth2Authentication) authentication;
            userDetails = (UserDetails) auth2Token.getUserAuthentication().getPrincipal();
        }else {
            throw new UnsupportedOperationException("unable to load useinfo");
        }
        UserInfoEnity userInfoEnity = extractOtherUserInfo((UserInfo) userDetails);
        return userInfoEnity;
    }

    private UserInfoEnity extractOtherUserInfo(UserInfo userInfo) {
        Collection<? extends GrantedAuthority> grantedAuthorities = userInfo.getAuthorities();
        Set<String> authorityStrs = grantedAuthorities
                .stream().map(x -> ((GrantedAuthority) x).getAuthority())
                .collect(Collectors.toSet());
        UserInfoEnity userInfoEnity = new UserInfoEnity();
        userInfoEnity.setUsername(userInfo.getUsername());
        userInfoEnity.setAuthorities(authorityStrs);
        userInfoEnity.setAuthorityEntities(new HashSet<>(userInfo.getBelongToRequestMap().values()));
        return userInfoEnity;
    }
}
