package com.scaler.ecommerceproject.tableinheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_instructor")
public class Instructor extends User {
    private String company;
}
