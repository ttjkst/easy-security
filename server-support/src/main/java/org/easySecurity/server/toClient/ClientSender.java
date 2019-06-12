package org.easySecurity.server.toClient;

import org.easySecurity.core.user.UserInfo;
import org.easySecurity.server.user.OAuth2UserWithMultOAuth2Info;

/**
 * send msg to client .
 * */
public interface ClientSender {

    void sendChangeUserInfoToClient(OAuth2UserWithMultOAuth2Info info);
}
