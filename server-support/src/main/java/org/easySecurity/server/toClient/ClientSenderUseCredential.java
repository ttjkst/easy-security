package org.easySecurity.server.toClient;

import org.easySecurity.core.user.ClientUserInfoEntity;
import org.easySecurity.core.user.UserInfoEntity;
import org.easySecurity.server.user.OAuth2UserWithMultOAuth2Info;
import org.easySecurity.server.utils.ServerConfigUtils;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class ClientSenderUseCredential implements ClientSender{



    public ClientSenderUseCredential() {

    }
    @Override
    public void sendChangeUserInfoToClient(OAuth2UserWithMultOAuth2Info userInfo) {
        Collection<ClientInfo> clientInfos = userInfo.getHasAuthorizedClientInfo();
        for (ClientInfo clientInfo : clientInfos) {
            ClientUserInfoEntity body   = extractBodyBean(userInfo, clientInfo.getAccessCode());
            RestTemplate restTemplate   = new RestTemplate();
            HttpHeaders headers         = new HttpHeaders();
            headers.setBearerAuth(clientInfo.getAccessCode());
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            Map<String, Object> information = clientInfo.getAdditionalInformation();
            String changeUrl = information.get("changeUrl").toString();
            URI uri = UriComponentsBuilder.fromUriString(changeUrl)
                    .build()
                    .toUri();
            RequestEntity<?> requestEntity = new RequestEntity<>(body,headers, HttpMethod.POST, uri);
            ResponseEntity<Void> exchange  = restTemplate.exchange(requestEntity, Void.TYPE);
        }

    }

    private ClientUserInfoEntity extractBodyBean(OAuth2UserWithMultOAuth2Info userInfo, String accessCode){
        UserInfoEntity userInfoEntity = ServerConfigUtils.extractOtherUserInfo(userInfo);
        ClientUserInfoEntity entity   = new ClientUserInfoEntity(userInfoEntity,accessCode);
        return  entity;
    }
}
