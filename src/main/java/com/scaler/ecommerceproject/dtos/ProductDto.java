package com.scaler.ecommerceproject.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private CategoryDto category;
}
