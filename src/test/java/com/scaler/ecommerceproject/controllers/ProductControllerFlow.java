package com.scaler.ecommerceproject.controllers;

import com.scaler.ecommerceproject.dtos.ProductDto;
import com.scaler.ecommerceproject.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlow {
    @Autowired
    private ProductController productController;

    @Autowired
    @Qualifier("Stub")
    private IProductService productService;

    @Test
    public void Test_ProductControllerFlow_RunsSuccessfully() {
        ProductDto productDto = ProductDto.builder()
                .id( 1L )
                .name( "iPhone20")
                .price( 120000D)
                .build();

        ProductDto response = productController.createProduct(productDto).getBody();
        ProductDto getResponse = productController.getProductById(response.getId()).getBody();
        productDto.setName("ChangedName");
        ProductDto putResponse = productController.replaceProduct(getResponse.getId(), productDto ).getBody();

        assertEquals( "iPhone20", getResponse.getName() );
        assertEquals( "ChangedName", putResponse.getName() );
    }
}
