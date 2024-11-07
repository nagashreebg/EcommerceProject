package com.scaler.ecommerceproject.controllers;

import com.scaler.ecommerceproject.dtos.ProductDto;
import com.scaler.ecommerceproject.models.Product;
import com.scaler.ecommerceproject.services.IProductService;
import com.scaler.ecommerceproject.utils.Convertion;
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
                HttpStatusCode.valueOf(200) );
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        headers.add("User-Agent", "Mozilla/5.0");
        headers.add( "called by", "smart frontend" );
        if( product != null )
            return new ResponseEntity<>(from(product), headers, HttpStatusCode.valueOf(200));
        else
            throw new IllegalArgumentException( "Product not found." );
    }

    @PostMapping()
    public ResponseEntity<ProductDto> getProduct( @RequestBody ProductDto productDto) {
        Product product = from( productDto );
        product = productService.createProduct( product );
        if( product != null )
            return new ResponseEntity<>(from(product), null, HttpStatusCode.valueOf(200));
        else
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> replaceProduct( @PathVariable("id") Long productId,
                                      @RequestBody ProductDto productDto) {
        Product product = from( productDto );
        productService.replaceProduct( productId, product );
        if( product != null )
            return new ResponseEntity<>(from(product), null, HttpStatusCode.valueOf(200));
        else
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(404));
    }

    public ProductDto from(Product product ) {
        return Convertion.getProductDto(product);
    }

    public Product from(ProductDto productDto ) {
        return Convertion.getProduct( productDto );
    }
}
