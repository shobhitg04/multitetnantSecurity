package com.example.armour.code.interview.tenant.service.impl;

import com.example.armour.code.interview.tenant.entity.Role;
import com.example.armour.code.interview.tenant.repository.RoleRepository;
import com.example.armour.code.interview.tenant.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**

 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
