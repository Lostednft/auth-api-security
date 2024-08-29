package com.example.auth_api_security;

import com.example.auth_api_security.domain.Product;
import com.example.auth_api_security.dtos.product.ProductRequestDTO;
import com.example.auth_api_security.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        productRepository.deleteAll();
    }

    @Test
    public void givenObjectProduct_whenCreateProduct_thenReturnProductSaved() throws Exception{

        //GIVEN
        Product product = new Product(new ProductRequestDTO("Teclado", 150));

        //WHEN
        ResultActions response = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));

        //THEN
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.price").value(product.getPrice()));
    }

    @Test
    public void givenListProduct_whenGetAllProduct_thenReturnAllProduct() throws Exception {

        //GIVEN
        List<Product> productList = List.of(
                new Product(new ProductRequestDTO("Tablet", 1350)),
                new Product(new ProductRequestDTO("Notebook", 4500)),
                new Product(new ProductRequestDTO("Celular", 3000)));

        productRepository.saveAll(productList);

        //WHEN
        ResultActions response = mockMvc.perform(get("/product"));

        //THEN
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[0].name").value(productList.getFirst().getName()));
    }
}
