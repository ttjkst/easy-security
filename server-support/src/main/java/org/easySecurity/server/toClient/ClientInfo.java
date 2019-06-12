package org.easySecurity.server.toClient;


import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public class ClientInfo extends BaseClientDetails {

    private String accessCode;


    public ClientInfo() {
    }

    public ClientInfo(ClientDetails prototype, String accessCode) {
        super(prototype);
        this.accessCode = accessCode;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }
}
