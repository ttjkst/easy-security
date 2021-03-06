package org.easySecutity.client.user;

import org.easySecurity.core.authority.AuthorityEntity;
import org.easySecurity.core.utils.AuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class OAuth2UserForUserInfo implements OAuth2User {

    private final LinkedHashMap<AntPathRequestMatcher, AuthorityEntity> belongToRequestMap;

    private final Collection<? extends GrantedAuthority> authorities;

    private final String name;

    private final String uniqueId;


    public OAuth2UserForUserInfo(Collection<? extends GrantedAuthority> authorities, String name, Set<AuthorityEntity> authorityInfos,String uniqueId) {
        this.authorities= authorities;
        this.name       = name;
        belongToRequestMap = new LinkedHashMap<>(authorityInfos.size());
        authorityInfos.forEach(authorityEntity -> belongToRequestMap.put(
                AuthorityUtils.mapToMatcher(authorityEntity,null)
                ,authorityEntity));
        this.uniqueId = uniqueId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        throw new  UnsupportedOperationException("no support");
    }

    @Override
    public String getName() {
        return name;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public LinkedHashMap<AntPathRequestMatcher, AuthorityEntity> getBelongToRequestMap() {
        return belongToRequestMap;
    }
}
