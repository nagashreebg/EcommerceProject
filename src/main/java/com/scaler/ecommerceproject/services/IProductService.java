package com.scaler.ecommerceproject.services;

import com.scaler.ecommerceproject.models.Product;

import java.util.List;

public interface IProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product replaceProduct(Long productId, Product product);
}
