package org.easySecutity.client.securityContext;


import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityContextRepositoryListenerAdpter implements SecurityContextRepositoryListener{

    @Override
    public void afterLoadContext(SecurityContext context) {

    }

    @Override
    public void beforeLoadContext(HttpRequestResponseHolder requestResponseHolder) {

    }

    @Override
    public void beforeSaveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

    }
}
