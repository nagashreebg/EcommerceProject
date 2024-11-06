package com.scaler.ecommerceproject.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@SuperBuilder
public class Product extends BaseModel{
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Category category;
}
