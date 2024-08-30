package com.example.auth_api_security;

import com.example.auth_api_security.domain.Product;
import com.example.auth_api_security.dtos.product.ProductRequestDTO;
import com.example.auth_api_security.dtos.product.ProductResponseDTO;
import com.example.auth_api_security.repositories.ProductRepository;
import com.example.auth_api_security.services.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductService productService;

    private ProductRepository productRepository;
    private Product product;
    private ProductRequestDTO productRequestDTO;


    @BeforeEach
    void setup() {
        productRepository = Mockito.mock(ProductRepository.class);
        productRequestDTO = new ProductRequestDTO("Mouse", 550);
        productRepository.deleteAll();
    }

    @Test
    void givenObjectProduct_whenSaveProduct_thenProductSaved() {

        product = new Product(productRequestDTO);

        //GIVEN
        BDDMockito.given(productService.saveProduct(productRequestDTO)).willReturn(product);

        //WHEN
        Product productSaved = productService.saveProduct(productRequestDTO);

        //THEN
        Assertions.assertThat(productSaved).isNotNull();
        Assertions.assertThat(productSaved.getName()).isEqualTo(product.getName());
        Assertions.assertThat(productSaved.getPrice()).isEqualTo(product.getPrice());

    }


    @Test
    void givenProductList_whenGetAllProduct_thenProductList() {

        List<Product> productList = List.of(
                new Product(productRequestDTO),
                new Product(new ProductRequestDTO("Teclado", 450)),
                new Product(new ProductRequestDTO("Monitor", 1200)));
        productRepository.saveAll(productList);

        //GIVEN
        BDDMockito.given(productRepository.findAll()).willReturn(productList);


        List<ProductResponseDTO> allProduct = productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();

        BDDMockito.given(productService.listProduct()).willReturn(allProduct);

        //WHEN
        List<ProductResponseDTO> productResponseDTOS = productService.listProduct();

        //THEN
        Assertions.assertThat(productResponseDTOS).isNotNull();
        Assertions.assertThat(productResponseDTOS.size()).isEqualTo(3);
    }
}