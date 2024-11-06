package com.scaler.ecommerceproject.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@lombok.experimental.SuperBuilder
public class Category extends BaseModel{
    private String name;
    private String description;

    private List<Product> products;
}
