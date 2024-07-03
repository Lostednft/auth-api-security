package com.example.auth_api_security;

import com.example.auth_api_security.controllers.ProductController;
import com.example.auth_api_security.domain.Product;
import com.example.auth_api_security.dtos.product.ProductRequestDTO;
import com.example.auth_api_security.repositories.ProductRepository;
import com.example.auth_api_security.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class getListProductTest {

    @Mock
    private ProductController productController;

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct_ProductAlreadyExists() {
        // Arrange
        var productRequest = new ProductRequestDTO("Marcos", 25);

        Product existingProduct = new Product();
        existingProduct.setName("Marcos");

        when(productRepository.findByName("Marcos")).thenReturn(existingProduct);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.saveProduct(productRequest);
        });

        // Verify
        assertEquals("Nome digitado ja existente!", exception.getMessage());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testSaveProduct_Success() {
        // Arrange

        var productRequest = new ProductRequestDTO("Marcos", 25);

        when(productRepository.findByName("Marcos")).thenReturn(null);

        Product savedProduct = new Product();
        savedProduct.setName("Marcos");

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        Product result = productService.saveProduct(productRequest);

        // Assert
        assertNotNull(result);
        assertEquals("Marcos", result.getName());
        verify(productRepository, times(1)).findByName("Marcos");
        verify(productRepository, times(1)).save(any(Product.class));
    }
}
