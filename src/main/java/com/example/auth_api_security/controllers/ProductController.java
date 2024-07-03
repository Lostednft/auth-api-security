package com.example.auth_api_security.controllers;

import com.example.auth_api_security.dtos.product.ProductRequestDTO;
import com.example.auth_api_security.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/product")
public class ProductController{

    private final ProductService productService;


    public ProductController (ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProductRequestDTO data){
        return ResponseEntity.ok(productService.saveProduct(data));
    }

    @GetMapping
    public ResponseEntity getAllProduct (){
        return ResponseEntity.ok(productService.listProduct());
    }
}
