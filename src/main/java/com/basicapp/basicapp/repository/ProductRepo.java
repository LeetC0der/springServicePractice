package com.basicapp.basicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basicapp.basicapp.models.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
    
}
