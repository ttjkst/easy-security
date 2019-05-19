package org.easySecurity.server.clientRegistration;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EasyMangerClientRegistrationRepository implements ClientRegistrationRepository ,Iterable<ClientRegistration>{

    private Map<String, ClientRegistration> registrations = new ConcurrentHashMap<>(255);

    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        return registrations.get(registrationId);
    }


    private static Map<String, ClientRegistration> createClientRegistrationIdToClientRegistration(Collection<ClientRegistration> registrations) {
        return Collections.unmodifiableMap(registrations.stream()
                .peek(registration -> Assert.notNull(registration, "registrations cannot contain null values"))
                .collect(Collectors.toMap(ClientRegistration::getRegistrationId, Function.identity())));
    }

    public void addAll(List<ClientRegistration> clientRegistrations){
        Map<String, ClientRegistration> idToClientRegistration = createClientRegistrationIdToClientRegistration(clientRegistrations);
        registrations.putAll(idToClientRegistration);
    }

    public void add(ClientRegistration clientRegistration){
        Assert.notNull(clientRegistration, "registration cannot contain null value");
        registrations.put(clientRegistration.getClientId(),clientRegistration);
    }

    public List<ClientRegistration> getAll(){
        return registrations.values().stream().collect(Collectors.toList());
    }

    @Override
    public Iterator<ClientRegistration> iterator() {
        return getAll().iterator();
    }
}
