package com.scaler.ecommerceproject.controllers;

import com.scaler.ecommerceproject.dtos.CategoryDto;
import com.scaler.ecommerceproject.dtos.ProductDto;
import com.scaler.ecommerceproject.models.Category;
import com.scaler.ecommerceproject.models.Product;
import com.scaler.ecommerceproject.services.IProductService;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( "products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("User-Agent", "Mozilla/5.0");
        headers.add( "called by", "smart frontend" );
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add( from( product ) );
        }
        return new ResponseEntity<List<ProductDto>>(productDtos, headers,
                HttpStatus.SC_OK );
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("User-Agent", "Mozilla/5.0");
        headers.add( "called by", "smart frontend" );
        if( product != null )
            return new ResponseEntity<>(from(product), headers, HttpStatus.SC_OK);
        else
            throw new IllegalArgumentException( "Product not found." );
    }

    @PostMapping()
    public ResponseEntity<ProductDto> getProduct( @RequestBody ProductDto productDto) {
        Product product = from( productDto );
        product = productService.createProduct( product );
        if( product != null )
            return new ResponseEntity<>(from(product), null, HttpStatus.SC_OK);
        else
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> replaceProduct( @PathVariable("id") Long productId,
                                      @RequestBody ProductDto productDto) {
        Product product = from( productDto );
        product = productService.replaceProduct( productId, product );
        if( product != null )
            return new ResponseEntity<>(from(product), null, HttpStatus.SC_OK);
        else
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    }

    public ProductDto from(Product product ) {
        ProductDto productDto = ProductDto.builder()
                                        .id(product.getId())
                                        .name(product.getName())
                                        .description(product.getDescription())
                                        .price(product.getPrice())
                                        .imageUrl(product.getImageUrl()).build();

        if( product.getCategory() != null ) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .id( product.getCategory().getId())
                    .name( product.getCategory().getName() )
                    .description( product.getCategory().getDescription()).build();
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }

    public Product from(ProductDto productDto ) {
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .imageUrl(productDto.getImageUrl()).build();

        if( productDto.getCategory() != null ) {
            Category category = Category.builder()
                    .id( productDto.getCategory().getId())
                    .name( productDto.getCategory().getName() )
                    .description( productDto.getCategory().getDescription()).build();
            product.setCategory(category);
        }
        return product;
    }
}
