package org.easySecurity.server.toClient;

import org.easySecurity.core.user.UserInfo;
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
    public void sendChangeUseInfoToClient(UserInfo info,String ...urls) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers       = new HttpHeaders();
        headers.setBearerAuth(accessCode);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }
}
