package org.easySecurity.server.toClient;

import org.easySecurity.core.user.UserInfo;

/**
 * send msg to client .
 * */
public interface ClientSender {

    void sendChangeUseInfoToClient(UserInfo info,String ...urls );
}
