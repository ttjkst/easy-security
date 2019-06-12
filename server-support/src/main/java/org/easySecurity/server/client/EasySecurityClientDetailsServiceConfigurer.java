package org.easySecurity.server.client;

import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;

@Deprecated
/**
 * maby delete in future
 * */
public class EasySecurityClientDetailsServiceConfigurer extends ClientDetailsServiceConfigurer {
    public EasySecurityClientDetailsServiceConfigurer(ClientDetailsServiceBuilder<?> builder) {
        super(builder);
    }
}
