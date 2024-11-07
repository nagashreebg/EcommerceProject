package com.scaler.ecommerceproject.stubs;

import com.scaler.ecommerceproject.models.Product;
import com.scaler.ecommerceproject.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service( "Stub")
public class ProductServiceStub implements IProductService {

    Map<Long, Product> productMap = new HashMap<>();

    @Override
    public Product getProductById(Long id) {
        return productMap.get(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productMap.values();
    }

    @Override
    public Product createProduct(Product product) {
        productMap.put(product.getId(), product);
        return productMap.get(product.getId());
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        productMap.put(productId, product);
        return productMap.get(productId);
    }
}
