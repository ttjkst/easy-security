package org.easySecutity.client.securityContext;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityContextRepositoryListener {
    void afterLoadContext(SecurityContext context);

    void beforeLoadContext(HttpRequestResponseHolder requestResponseHolder);

    void beforeSaveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response);

}
