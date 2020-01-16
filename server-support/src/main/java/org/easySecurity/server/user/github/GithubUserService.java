package org.easySecurity.server.user.github;

import org.easySecurity.core.user.UserInfo;
import org.easySecurity.server.user.OAuth2ServerUserService;
import org.easySecurity.server.user.OAuth2UserWithMultOAuth2Info;
import org.easySecurity.server.user.UniqueProvider;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GithubUserService implements OAuth2ServerUserService<OAuth2UserRequest, OAuth2User> {

    private UniqueProvider uniqueProvider;

    public GithubUserService(UniqueProvider uniqueProvider) {
        this.uniqueProvider = uniqueProvider;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers       = new HttpHeaders();
        headers.setBearerAuth(userRequest.getAccessToken().getTokenValue());
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        try {
            URI uri = UriComponentsBuilder.fromUriString(userRequest.getClientRegistration()
                    .getProviderDetails().getUserInfoEndpoint().getUri())
                    .build()
                    .toUri();
            RequestEntity<?> requestEntity         = new RequestEntity<>(headers, HttpMethod.GET, uri);
            ResponseEntity<GithubUser> exchange    = restTemplate.exchange(requestEntity, GithubUser.class);
            GithubUser githubUser                  = exchange.getBody();
            if(githubUser==null){
                return  null;
            }
            UserInfo userInfo = uniqueProvider.extractUseDetails(githubUser);
            Map<String,Object> extra = new HashMap<>(2);
            extra.put(OAuth2UserWithMultOAuth2Info.GITHUB_USER,githubUser);
            extra.put(OAuth2UserWithMultOAuth2Info.GITHUB_USER_ACCESS_CODE,userRequest.getAccessToken());
            return new OAuth2UserWithMultOAuth2Info(userInfo,extra);
        }catch (Exception e){
            throw new RuntimeException("can not load userInfo for authoriaztion server",e);
        }
    }

    @Override
    public boolean support(ClientRegistration clientRegistration) {
        return "GitHub".equals(clientRegistration.getClientName());
    }
}
