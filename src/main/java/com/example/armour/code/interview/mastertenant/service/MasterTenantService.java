package com.example.armour.code.interview.mastertenant.service;

import com.example.armour.code.interview.mastertenant.entity.MasterTenant;

/**

 */
public interface MasterTenantService {

    MasterTenant findByClientId(Integer clientId);
}
