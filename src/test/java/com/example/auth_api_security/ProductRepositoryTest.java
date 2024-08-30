package com.example.auth_api_security;

import com.example.auth_api_security.domain.Product;
import com.example.auth_api_security.dtos.product.ProductRequestDTO;
import com.example.auth_api_security.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product product;


    @BeforeEach
    void setup(){
        productRepository.deleteAll();
    }

    @Test
    void givenProductName_whenFindProductByName_thenReturnProductObject(){

        //GIVEN
        product = new Product(new ProductRequestDTO("Teclado", 650));
        productRepository.save(product);
        //WHEN
        Product productByName = productRepository.findByName(product.getName());
        //THEN
        Assertions.assertThat(productByName.getName()).isEqualTo(product.getName());
    }

    @Test
    void givenProductList_whenFindAllProduct_thenReturnProductList(){

        //GIVEN
        List<Product> productList = List.of(
                new Product(new ProductRequestDTO("Teclado", 650)),
                new Product(new ProductRequestDTO("Mouse", 860)));
        productRepository.saveAll(productList);
        //WHEN
        List<Product> productAll = productRepository.findAll();
        //THEN
        Assertions.assertThat(productAll.size()).isEqualTo(productList.size());
    }
}
