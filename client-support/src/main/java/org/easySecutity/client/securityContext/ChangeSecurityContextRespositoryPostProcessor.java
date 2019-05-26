package org.easySecutity.client.securityContext;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

public class ChangeSecurityContextRespositoryPostProcessor implements ObjectPostProcessor<SecurityContextPersistenceFilter> {

    private SecurityContextRepositoryWapper securityContextRepositoryWapper;

    @Override
    public <O extends SecurityContextPersistenceFilter> O postProcess(O object) {

        return object;
    }
}
