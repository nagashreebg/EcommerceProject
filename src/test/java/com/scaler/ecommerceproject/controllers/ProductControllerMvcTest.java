package com.scaler.ecommerceproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.ecommerceproject.dtos.ProductDto;
import com.scaler.ecommerceproject.models.Product;
import com.scaler.ecommerceproject.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void Test_GetAllProducts_RunsSuccessfully() throws Exception {
        // Arrange
        List<Product> products = new ArrayList<>();

        products.add ( Product.builder()
                .id( 1L )
                .name( "iPhone16")
                .price( 120000D)
                .build() );
        products.add ( Product.builder()
                .id( 2L )
                .name( "Macbook Pro" )
                .price( 170000D)
                .build() );

        when( productService.getAllProducts() ).thenReturn(products);

        mockMvc.perform( get("/products" ))
                .andExpect( status().isOk() )
                .andExpect( content().string(objectMapper.writeValueAsString(products)))
                .andExpect(jsonPath("$[0].name").value("iPhone16"))
                .andExpect( jsonPath( "$[0].length()").value(3))
                .andExpect(jsonPath("$.length()").value(2));;

    }

    @Test
    public void Test_PostProduct_RunsSuccessfully() throws Exception {

        // Arrange
        ProductDto productDto = ProductDto.builder()
                .id( 1L )
                .name( "iPhone16")
                .price( 120000D)
                .build();
        Product product = Product.builder()
                                .id( 1L )
                                .name( "iPhone16")
                                .price( 120000D)
                                .build();

        when( productService.createProduct(any(Product.class)) ).thenReturn(product);

        mockMvc.perform( post("/products").content(objectMapper.writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect( status().isOk() )
                .andExpect( content().string(objectMapper.writeValueAsString(productDto)))
                .andExpect(jsonPath("$.name").value("iPhone16"))
                .andExpect( jsonPath( "$.length()").value(3));
    }
}
