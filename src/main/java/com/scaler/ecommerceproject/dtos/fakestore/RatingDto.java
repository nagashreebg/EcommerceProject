package com.scaler.ecommerceproject.dtos.fakestore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RatingDto {
    private Double rate;
    private int count;
}
