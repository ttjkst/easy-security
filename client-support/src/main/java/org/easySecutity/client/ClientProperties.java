package org.easySecutity.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "easySpring.oauth2.client")
public class ClientProperties {

    private boolean disableListeningServerUseInfoChange = false;

    public boolean isDisableListeningServerUseInfoChange() {
        return disableListeningServerUseInfoChange;
    }

    public void setDisableListeningServerUseInfoChange(boolean disableListeningServerUseInfoChange) {
        this.disableListeningServerUseInfoChange = disableListeningServerUseInfoChange;
    }
}
