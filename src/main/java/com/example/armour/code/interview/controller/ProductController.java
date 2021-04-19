package com.example.armour.code.interview.controller;

import com.example.armour.code.interview.security.RequestAuthorization;
import com.example.armour.code.interview.tenant.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * Product Controller to check the access control.
 */
@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController implements Serializable {

        @Autowired
        ProductService  productService;

        @RequestAuthorization
        @PreAuthorize("hasPermission('hasAccess','allproduct')")
        @RequestMapping(value = "/all", method = RequestMethod.GET)
        public ResponseEntity<Object> getAllProduct() {
            log.info("getAllProduct() method call...");
            return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
        }

    @RequestAuthorization
    @PreAuthorize("hasPermission('hasAccess','checkPermission')")
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public ResponseEntity<Object> checkPermission() {
        log.info("getAllProduct() method call...");
        return new ResponseEntity<>("checked successfully",HttpStatus.OK);
    }

}
