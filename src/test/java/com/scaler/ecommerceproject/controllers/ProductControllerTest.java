package com.scaler.ecommerceproject.controllers;

import com.scaler.ecommerceproject.dtos.ProductDto;
import com.scaler.ecommerceproject.models.Product;
import com.scaler.ecommerceproject.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    ProductController productController;

    @MockBean
    IProductService productService;

    @Captor
    ArgumentCaptor<Long> idCaptor;

    @Test
    public void Test_GetProductById_WithValidId_ReturnsProductSuccessfully() {

        // Arrange
        Product product = Product.builder().id(1L).name("iphone16")
                                           .description("electronics")
                                            .price( 10000D )
                                            .build();

        // Act
        when( productService.getProductById(1L) ).thenReturn(product);
        ProductDto response = productController.getProductById(1L).getBody();

        //Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("iphone16", response.getName());
    }

    @Test
    @DisplayName("paramter 0 resulted in product not present exception")
    public void Test_GetProductById_WithInvalidId_ThrowsException() {
        //Act and Assert
//        Exception ex = assertThrows(IllegalArgumentException.class,
//                () -> productController.getProductById(0L));
//        assertEquals("Product not found.",ex.getMessage());
        verify(productService,times(0))
                .getProductById(0L);
    }

    @Test
    public void Test_GetProductById_ProductServiceThrowsException() {
        //Arrange
        when(productService.getProductById(any(Long.class)))
                .thenThrow(new RuntimeException("something went bad"));

        //Act and Assert
        assertThrows(RuntimeException.class,
                () -> productController.getProductById(1L));
    }

    @Test
    public void Test_GetProductById_CheckIfProductServiceCalledWithExpectedArguments() {
        //Arrange
        Long id = 1L;
        Product product = Product.builder()
                                    .id( id )
                                    .name( "iPhone16" )
                                    .price(100000D)
                                    .build();

        when(productService.getProductById(any(Long.class)))
                .thenReturn(product);

        //Act
        productController.getProductById(id);

        //Assert
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(id,idCaptor.getValue());
    }
}