package org.easySecurity.server;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "easy-security.oauth2.server")
public class ServerConfigProperties {

    private Map<String,ClientInfo> clientInfo = new HashMap<>();

    public static class  ClientInfo{
        private String sendToClientAuthenticationType;

        private String changeUseInfoUrl;

        public String getSendToClientAuthenticationType() {
            return sendToClientAuthenticationType;
        }

        public void setSendToClientAuthenticationType(String sendToClientAuthenticationType) {
            this.sendToClientAuthenticationType = sendToClientAuthenticationType;
        }

        public String getChangeUseInfoUrl() {
            return changeUseInfoUrl;
        }

        public void setChangeUseInfoUrl(String changeUseInfoUrl) {
            this.changeUseInfoUrl = changeUseInfoUrl;
        }
    }
}
