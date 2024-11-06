package com.scaler.ecommerceproject.tableinheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name="msc_ta") // You can specify the table name here if needed
public class TA extends User {
    private Double ratings;
}
