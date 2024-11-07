package com.scaler.ecommerceproject.utils;

import com.scaler.ecommerceproject.dtos.CategoryDto;
import com.scaler.ecommerceproject.dtos.ProductDto;
import com.scaler.ecommerceproject.models.Category;
import com.scaler.ecommerceproject.models.Product;

public class Convertion {
    public static ProductDto getProductDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price( product.getPrice())
                .imageUrl(product.getImageUrl()).build();
        if( product.getCategory() != null ) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .id(product.getCategory().getId())
                    .name(product.getCategory().getName())
                    .description(product.getCategory().getDescription())
                    .build();
            productDto.setCategory( categoryDto );
        }
        return productDto;
    }

    public static Product getProduct(ProductDto productDto) {
        return from(productDto);
    }

    public static Product from(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price( productDto.getPrice())
                .imageUrl(productDto.getImageUrl()).build();
        if( productDto.getCategory() != null ) {
            Category category = Category.builder()
                    .id(productDto.getCategory().getId())
                    .name(productDto.getCategory().getName())
                    .description(productDto.getCategory().getDescription())
                    .build();
            product.setCategory( category );
        }
        return product;
    }
}