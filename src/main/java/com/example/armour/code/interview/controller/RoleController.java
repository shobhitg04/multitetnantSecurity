package com.example.armour.code.interview.controller;

import com.example.armour.code.interview.security.RequestAuthorization;
import com.example.armour.code.interview.tenant.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@Slf4j
@RestController
@RequestMapping("/api/role")
public class RoleController implements Serializable {

    @Autowired
    RoleService roleService;

    @RequestAuthorization
    @PreAuthorize("hasPermission('hasAccess','allrole')")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllRoles() {

        log.info("getAllProduct() method call...");

        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
}
