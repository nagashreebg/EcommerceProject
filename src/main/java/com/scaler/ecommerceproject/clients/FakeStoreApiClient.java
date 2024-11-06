package com.scaler.ecommerceproject.clients;

import com.scaler.ecommerceproject.dtos.fakestore.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreApiClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto) {
        fakeStoreProductDto = requestForEntity(HttpMethod.PUT,
                "http://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class ).getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto replaceProduct(Long id, FakeStoreProductDto fakeStoreProductDto) {
        fakeStoreProductDto = requestForEntity(HttpMethod.PUT,
                "http://fakestoreapi.com/products/{id}",
                fakeStoreProductDto,
                FakeStoreProductDto.class, id ).getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto[] getAllProducts() {
        FakeStoreProductDto [] fakeStoreProductDtos =
                requestForEntity( HttpMethod.GET, "http://fakestoreapi.com/products", null, FakeStoreProductDto[].class).getBody();
        return fakeStoreProductDtos;
    }

    public FakeStoreProductDto getProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto =
                requestForEntity( HttpMethod.GET,
                        "http://fakestoreapi.com/products/{id}",
                        null,
                        FakeStoreProductDto.class,
                        id );

        if( fakeStoreProductDto.getStatusCode() == HttpStatusCode.valueOf(200) &&
                fakeStoreProductDto.getBody() != null)
            return fakeStoreProductDto.getBody();

        return null;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return (ResponseEntity)((ResponseEntity)restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables));
    }


}
