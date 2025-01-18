package com.basicapp.basicapp.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.basicapp.basicapp.models.Product;
import com.basicapp.basicapp.repository.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ResponseEntity<Product> getProductById(Long id){
        try{
            if(!productRepo.existsById(id)){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Optional<Product> response = productRepo.findById(id);
            return new ResponseEntity<>(response.get(), HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ArrayList<Product>> getAllProducts(){
        try {
            ArrayList<Product> result = new ArrayList<>();
            productRepo.findAll().forEach(result::add);
            if(result.isEmpty()){
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Product> insertNewProduct(Product newProduct){
        try {
            Product insertedProduct = productRepo.save(newProduct);
            return new ResponseEntity<>(insertedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<Product> updateProdcutById(Long id, Product product){
        try {
            Optional<Product> productById = productRepo.findById(id);
            if(!productById.isPresent()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Product updateProduct = productById.get();
            updateProduct.setName(product.getName());
            updateProduct.setAuthor(product.getAuthor());
            Product updatedProduct = productRepo.save(updateProduct);
            return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    public ResponseEntity<String> deleteById(Long id){
        try {
            if(id == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            productRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
