package com.example.armour.code.interview.mastertenant.service;

import com.example.armour.code.interview.mastertenant.entity.MasterTenant;
import com.example.armour.code.interview.mastertenant.repository.MasterTenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**

 */
@Service
@Slf4j
public class MasterTenantServiceImpl implements MasterTenantService{


    @Autowired
    MasterTenantRepository masterTenantRepository;


    @Override
    public MasterTenant findByClientId(Integer clientId) {
        log.info("findByClientId() method call...");
        return masterTenantRepository.findByTenantClientId(clientId);
    }
}
