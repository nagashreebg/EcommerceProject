package com.scaler.ecommerceproject.services;

import com.scaler.ecommerceproject.clients.FakeStoreApiClient;
import com.scaler.ecommerceproject.dtos.fakestore.FakeStoreProductDto;
import com.scaler.ecommerceproject.models.Category;
import com.scaler.ecommerceproject.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto =
                fakeStoreApiClient.getProductById( id );
        if( fakeStoreProductDto != null)
            return from( fakeStoreProductDto );
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto [] fakeStoreProductDtos = fakeStoreApiClient.getAllProducts();
        ArrayList<Product> products = new ArrayList<>();
        for( FakeStoreProductDto f : fakeStoreProductDtos ) {
            products.add( from( f ) );
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        if( product == null )
            return null;
        return from( fakeStoreApiClient.createProduct( from( product) ) );
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.replaceProduct(id, from( product ) );
        return from (fakeStoreProductDto );
    }

    public Product from(FakeStoreProductDto fakeStoreProductDto) {
        if( fakeStoreProductDto == null )
            return null;

        Product product = Product.builder()
                .id(fakeStoreProductDto.getId())
                .name(fakeStoreProductDto.getTitle())
                .description(fakeStoreProductDto.getDescription())
                .price( Double.valueOf(fakeStoreProductDto.getPrice() ) )
                .imageUrl(fakeStoreProductDto.getImage()).build();

        if( fakeStoreProductDto.getCategory() != null ) {
            Category category = Category.builder()
                    .name( fakeStoreProductDto.getCategory()).build();
            product.setCategory( category );
        }
        return product;
    }

    public FakeStoreProductDto from( Product product) {
        if( product == null )
            return null;

        FakeStoreProductDto fakeStoreProductDto = FakeStoreProductDto.builder()
                .id(product.getId())
                .title(product.getName())
                .description(product.getDescription())
                .price( product.getPrice())
                .image(product.getImageUrl()).build();
        if( product.getCategory() != null ) {
            fakeStoreProductDto.setCategory( product.getCategory().getName() );
        }
        return fakeStoreProductDto;
    }
}
