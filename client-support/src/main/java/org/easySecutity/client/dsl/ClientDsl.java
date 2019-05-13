package org.easySecutity.client.dsl;

import org.easySecurity.core.voters.AddVotersToFilterSecurityInterceptorHelper;
import org.easySecurity.core.voters.UserInfoWebExpresssionClientAuthorityVoter;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.FilterInvocation;

import java.util.ArrayList;
import java.util.List;

public class ClientDsl extends AbstractHttpConfigurer<ClientDsl, HttpSecurity> {

    private List<AccessDecisionVoter<? extends Object>> webCilentVoters = new ArrayList<>(10);

    @Override
    public void init(HttpSecurity builder) throws Exception {
        if(webCilentVoters.isEmpty()){
            webCilentVoters.add(new UserInfoWebExpresssionClientAuthorityVoter());
        }
        builder.authorizeRequests().withObjectPostProcessor(new AddVotersToFilterSecurityInterceptorHelper(webCilentVoters));
    }

    public ClientDsl voter(AccessDecisionVoter<FilterInvocation> voter){
        webCilentVoters.clear();
        webCilentVoters.add(voter);
        return this;
    }

    public ClientDsl voters(List<AccessDecisionVoter<FilterInvocation>> voters){
        webCilentVoters.clear();
        webCilentVoters.addAll(voters);
        return this;
    }

    public ClientDsl addVoters(List<AccessDecisionVoter<FilterInvocation>> voters){
        webCilentVoters.addAll(voters);
        determineDefalutVoterIfNoneAddIt();
        return this;
    }

    public ClientDsl addVoter(AccessDecisionVoter<FilterInvocation> voter){
        webCilentVoters.add(voter);
        determineDefalutVoterIfNoneAddIt();
        return this;
    }

    private void determineDefalutVoterIfNoneAddIt(){
        for (AccessDecisionVoter<?> webCilentVoter : webCilentVoters) {
            if(webCilentVoter instanceof UserInfoWebExpresssionClientAuthorityVoter){
                return;
            }
        }
        webCilentVoters.add(new UserInfoWebExpresssionClientAuthorityVoter());
    }
}
