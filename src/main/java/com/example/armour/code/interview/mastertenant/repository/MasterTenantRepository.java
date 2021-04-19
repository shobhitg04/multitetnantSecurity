package com.example.armour.code.interview.mastertenant.repository;

import com.example.armour.code.interview.mastertenant.entity.MasterTenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**

 */
@Repository
public interface MasterTenantRepository extends JpaRepository<MasterTenant, Integer> {
    MasterTenant findByTenantClientId(Integer clientId);
}
