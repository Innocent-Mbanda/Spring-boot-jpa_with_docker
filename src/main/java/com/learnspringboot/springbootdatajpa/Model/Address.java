package com.learnspringboot.springbootdatajpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer addressId;
    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house_number", nullable = false)
    private int houseNumber;

    @Column(name = "zip_code", nullable = false)
    private float zipCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee")
   private  Employee employee;

}
