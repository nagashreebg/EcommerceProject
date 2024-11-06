package com.scaler.ecommerceproject.tableinheritanceexamples.mappedsuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {

    @Id
    private Long id;

    private String name;

    private String email;
}
