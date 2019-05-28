package org.easySecurity.server.toClient;

import org.easySecurity.server.user.OAuth2UserWithMultOAuth2Info;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class ClientSenderUseCredential implements ClientSender{

    private final String accessCode;


    public ClientSenderUseCredential(String accessCode) {
        this.accessCode = accessCode;
    }
    @Override
    public void sendChangeUseInfoToClient(OAuth2UserWithMultOAuth2Info userInfo) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers       = new HttpHeaders();
        headers.setBearerAuth(accessCode);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }
}
