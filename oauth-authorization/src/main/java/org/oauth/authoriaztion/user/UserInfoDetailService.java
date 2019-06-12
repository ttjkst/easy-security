package org.oauth.authoriaztion.user;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.easySecurity.core.authority.AuthorityEntity;
import org.easySecurity.core.user.UserInfo;
import org.easySecurity.core.user.UserInfoEntity;
import org.easySecurity.server.user.UniqueAccessor;
import org.easySecurity.server.user.UniqueProvider;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class UserInfoDetailService implements UserDetailsService , UniqueProvider {


    private static List<UserInfo> userInfos;

    private static Log logger = LogFactory.getLog(UserInfoDetailService.class);

    private String path ="userInfos.json";

    private final String premitAll="permitAll()";


    private ObjectMapper objectMapper = new ObjectMapper();


    public UserInfoDetailService() throws IOException {

        ClassPathResource fileRource = new ClassPathResource( path );
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionLikeType(List.class, UserInfoEntity.class);
        Collection<UserInfoEntity> userInfoEnities = (Collection<UserInfoEntity>) objectMapper.readValue(fileRource.getFile(), javaType);
        userInfos =  userInfoEnities.stream().map( this::mapToUserInfo).collect( Collectors.toList());

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username==null||!StringUtils.hasText( username )){
            throw new UsernameNotFoundException( "null or empty username not exist!");
        }

        Optional<UserInfo> first = userInfos.stream().filter( x -> x.getUsername().equals( username ) ).findFirst();
        return first.orElseThrow( ()->{
            logger.info(String.format("current userInfoResource is "+userInfos));
               return  new UsernameNotFoundException( String.format( " witch name %s can not found userInfo",username));
        });
    }

    private UserInfo mapToUserInfo(UserInfoEntity enity){
        Set<AuthorityEntity> entities = enity.getAuthorityEntities();
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = enity.getAuthorities()
                .stream()
                .map( SimpleGrantedAuthority::new )
                .collect( Collectors.toSet());
        //due to spring default password encoder will be configured,use default password encoder --bcrypt
        String encodePassword = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(enity.getPassword());
        return new UserInfo(enity.getUsername(),encodePassword,simpleGrantedAuthorities,entities,enity.getExtraInfo());
    }

    @Override
    public UserInfo extractUseDetails(UniqueAccessor accessor) {

        for (UserInfo userInfo : userInfos) {
            if(userInfo.getExtraInfo().containsKey(accessor.type())) {
                Map map = (Map) userInfo.getExtraInfo().get(accessor.type());
                if(accessor.uniqueId().equals(map.get("id").toString())) {
                    return userInfo;
                }
            }
        }
        return null;
    }
}
