package com.Merlin.eshop.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "seller")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;


    int age;

    @Column(unique = true)
    String email;

    @Column(unique = true)
    String mobile;

    String enterprise;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();
}
