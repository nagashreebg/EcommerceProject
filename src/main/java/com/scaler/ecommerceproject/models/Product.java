package com.scaler.ecommerceproject.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@SuperBuilder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{
    private String name;
    private String description;
    private String imageUrl;
    private Double price;

    @ManyToOne( fetch = FetchType.LAZY )
    private Category category;
}
