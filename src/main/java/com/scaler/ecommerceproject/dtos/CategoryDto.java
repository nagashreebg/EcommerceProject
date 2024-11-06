package com.scaler.ecommerceproject.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private List<ProductDto> products;
}
