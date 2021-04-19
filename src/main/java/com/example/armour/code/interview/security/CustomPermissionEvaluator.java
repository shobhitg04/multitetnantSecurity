package com.example.armour.code.interview.security;

import com.example.armour.code.interview.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    RoleHierarchyImpl roleHierarchy;


    @Override
    public boolean hasPermission(Authentication authentication, Object accessType, Object permission) {
        if (authentication != null && accessType instanceof String) {
            if ("hasAccess".equalsIgnoreCase(String.valueOf(accessType))) {
                return hasPrivilege(authentication, String.valueOf(accessType), String.valueOf(permission));
            }
            return false;
        }
        return false;
    }

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        roleHierarchy = BeanUtil.getBean(RoleHierarchyImpl.class);
        if (Objects.nonNull(auth.getAuthorities()) && auth.getAuthorities().size() > 0) {
            Optional<GrantedAuthority> authFind = roleHierarchy.getReachableGrantedAuthorities(
                    auth.getAuthorities())
                    .stream().filter(grantedAuth -> grantedAuth.getAuthority().equals(permission)).findFirst();
            return authFind.isPresent();
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String targetType,
                                 Object permission) {
        return false;
    }

}
