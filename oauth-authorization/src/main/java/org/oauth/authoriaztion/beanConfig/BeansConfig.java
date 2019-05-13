package org.oauth.authoriaztion.beanConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.github.easy.security.core.token.TokenStoreUseTokenEnhancer;
import org.oauth.authoriaztion.keyPair.KeyPairUtils;
import org.oauth.authoriaztion.user.UserInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;


@Configuration
public class BeansConfig {


    private static Log logger = LogFactory.getLog(BeansConfig.class);
//    @Bean
//    public TokenStore redisTokenStore(@Autowired RedisConnectionFactory redisConnectionFactory){
//        return  new RedisTokenStore(redisConnectionFactory);
//    }

    @Autowired
    private KeyPair keyPair;


    @Bean
    public TokenStoreUseTokenEnhancer tokenStoreUseTokenEnhancer(){
        return new TokenStoreUseTokenEnhancer();
    }

    @Bean
    public PasswordEncoder passwordEncoderDefinded(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() throws IOException {
        return  new UserInfoDetailService();
    }

    @Bean
    public  KeyPair  generateKeyPair() throws NoSuchAlgorithmException {
      return KeyPairUtils.getKey();
    }


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() throws NoSuchAlgorithmException {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair);
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        converter.setAccessTokenConverter(accessTokenConverter);
        return converter;
    }


}
