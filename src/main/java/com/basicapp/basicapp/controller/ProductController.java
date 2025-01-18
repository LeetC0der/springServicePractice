package com.basicapp.basicapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basicapp.basicapp.models.Product;
import com.basicapp.basicapp.services.ProductService;





@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<ArrayList<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Product> postMethodName(@RequestBody Product product) {
        return productService.insertNewProduct(product);
    }  
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> putMethodName(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProdcutById(id, product);
    }
}
