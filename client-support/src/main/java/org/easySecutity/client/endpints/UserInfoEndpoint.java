package org.easySecutity.client.endpints;

import org.easySecurity.core.user.ClientUserInfoEntity;
import org.easySecutity.client.securityContext.ChangeUseInfoExecutor;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@FrameworkEndpoint
public class UserInfoEndpoint {

    private ChangeUseInfoExecutor executor;

    @RequestMapping("/user/info/update")
    @ResponseBody
    public void updateUseInfo(@RequestBody List<ClientUserInfoEntity> userInfoEnities){
        executor.addChangeToWaitMap(userInfoEnities);
    }

    public ChangeUseInfoExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ChangeUseInfoExecutor executor) {
        this.executor = executor;
    }
}
