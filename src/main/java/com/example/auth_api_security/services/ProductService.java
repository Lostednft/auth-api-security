package com.example.auth_api_security.services;

import com.example.auth_api_security.domain.Product;
import com.example.auth_api_security.dtos.product.ProductRequestDTO;
import com.example.auth_api_security.dtos.product.ProductResponseDTO;
import com.example.auth_api_security.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService{


    private final ProductRepository repository;


    public ProductService(ProductRepository productRepository){
        this.repository = productRepository;
    }

    @Transactional
    public Product saveProduct(ProductRequestDTO productRequestDTO) {

        var product = new Product(productRequestDTO);

        if (repository.findByName(product.getName()) != null) throw new RuntimeException("Nome digitado ja existente!");

        return repository.save(product);
    }



    public List<ProductResponseDTO> listProduct(){

        return repository.findAll().stream()
                .map(ProductResponseDTO::new)
                .toList();
    }
}