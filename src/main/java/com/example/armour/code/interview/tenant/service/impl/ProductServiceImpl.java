package com.example.armour.code.interview.tenant.service.impl;

import com.example.armour.code.interview.tenant.entity.Product;
import com.example.armour.code.interview.tenant.repository.ProductRepository;
import com.example.armour.code.interview.tenant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**

 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
