package com.devstack.healthcare.system.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserRole {

//    DOCTOR(permissions),
//    ADMIN(permissions);
    DOCTOR(new HashSet<>(Set.of(ApplicationUserPermission.DOCTOR_READ, ApplicationUserPermission.DOCTOR_WRITE))),
    ADMIN(new HashSet<>(Set.of(ApplicationUserPermission.DOCTOR_READ, ApplicationUserPermission.DOCTOR_WRITE)));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
