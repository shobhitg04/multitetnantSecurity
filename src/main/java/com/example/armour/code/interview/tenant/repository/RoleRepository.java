package com.example.armour.code.interview.tenant.repository;

import com.example.armour.code.interview.tenant.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**

 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
