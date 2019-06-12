package org.easySecurity.server.utils;

import org.easySecurity.core.user.UserInfo;
import org.easySecurity.core.user.UserInfoEntity;
import org.easySecurity.core.utils.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ServerConfigUtils extends SecurityUtils {

    public static UserInfoEntity extractOtherUserInfo(UserInfo userInfo) {
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
