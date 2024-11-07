package com.scaler.ecommerceproject.services;

import com.scaler.ecommerceproject.dtos.CategoryDto;
import com.scaler.ecommerceproject.dtos.ProductDto;
import com.scaler.ecommerceproject.dtos.fakestore.FakeStoreProductDto;
import com.scaler.ecommerceproject.models.Category;
import com.scaler.ecommerceproject.models.Product;
import com.scaler.ecommerceproject.repositories.ProductRepo;
import com.scaler.ecommerceproject.utils.Convertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StoreProductService implements IProductService{

    @Autowired
    ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        Optional<Product> productOptional = productRepo.findById(productId);
        if( productOptional.isPresent() ) {
            Product curProduct = productOptional.get();
            curProduct.setName(product.getName());
            curProduct.setDescription(product.getDescription());
            curProduct.setPrice(product.getPrice());
            if( product.getCategory() != null ) {
                curProduct.setCategory(product.getCategory());
            }
            curProduct.setImageUrl(product.getImageUrl());
            curProduct = productRepo.save(curProduct);
            return curProduct;
        }
        return null;
    }

    public ProductDto from(Product product) {
        if( product == null )
            return null;
        return Convertion.getProductDto(product);
    }
}
