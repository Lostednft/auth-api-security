package com.example.auth_api_security.repositories;

import com.example.auth_api_security.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    public Product findByName(String username);
}
