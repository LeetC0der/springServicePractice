package com.basicapp.basicapp.crudApp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.basicapp.basicapp.models.Product;
import com.basicapp.basicapp.repository.ProductRepo;
import com.basicapp.basicapp.services.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServicesTest {


    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductService productService;

    @Test
    public void addProduct(){
        Product pd = new Product();
        pd.setAuthor("Volvo");
        pd.setName("Volskvagen");
        Mockito.when(productRepo.save(pd)).thenReturn(pd);
        ResponseEntity<Product> addNewProduct = productService.insertNewProduct(pd);
        Assertions.assertEquals(pd.getName(), addNewProduct.getBody().getName());
    }
}
