package com.example.auth_api_security.dtos.product;

import com.example.auth_api_security.domain.Product;

public record ProductResponseDTO (String id, String name, Integer price){

    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(), product.getPrice());
    }
}
