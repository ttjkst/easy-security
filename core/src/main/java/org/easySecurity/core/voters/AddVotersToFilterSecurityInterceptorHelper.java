package org.easySecurity.core.voters;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.List;

public class AddVotersToFilterSecurityInterceptorHelper implements ObjectPostProcessor<FilterSecurityInterceptor> {

    private List<AccessDecisionVoter<? extends Object>> voterListToAdd;


    public AddVotersToFilterSecurityInterceptorHelper(List<AccessDecisionVoter<? extends Object>> voterListToAdd) {
        this.voterListToAdd = voterListToAdd;
    }

    @Override
    public FilterSecurityInterceptor postProcess(FilterSecurityInterceptor securityInterceptor) {
            AccessDecisionManager accessDecisionManager = securityInterceptor.getAccessDecisionManager();
            if(accessDecisionManager instanceof AbstractAccessDecisionManager){
                ((AbstractAccessDecisionManager) accessDecisionManager).getDecisionVoters().addAll(voterListToAdd);
            }

        return securityInterceptor;
    }

}
