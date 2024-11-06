package com.scaler.ecommerceproject.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Setter
@Getter
@JsonInclude( JsonInclude.Include.NON_NULL )
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseModel {
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
