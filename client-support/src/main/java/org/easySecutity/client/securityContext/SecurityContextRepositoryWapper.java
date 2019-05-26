package org.easySecutity.client.securityContext;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SecurityContextRepositoryWapper implements SecurityContextRepository {

    private SecurityContextRepository contextRepository;

    private List<SecurityContextRepositoryListener> listeners;

    public SecurityContextRepositoryWapper(SecurityContextRepository contextRepository, List<SecurityContextRepositoryListener> listeners) {
        this.contextRepository = contextRepository;
        this.listeners = listeners;
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        for (SecurityContextRepositoryListener listener : listeners) {
            listener.beforeLoadContext(requestResponseHolder);
        }
        SecurityContext securityContext = contextRepository.loadContext(requestResponseHolder);
        for (SecurityContextRepositoryListener listener : listeners) {
            listener.afterLoadContext(securityContext);
        }
        return securityContext;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        for (SecurityContextRepositoryListener listener : listeners) {
            listener.beforeSaveContext(context,request,response);
        }
        contextRepository.saveContext(context,request,response);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
       return  contextRepository.containsContext(request);
    }
}
