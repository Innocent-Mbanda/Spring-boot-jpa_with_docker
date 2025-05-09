package com.learnspringboot.springbootdatajpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "employee_identifier",nullable = false, unique = true)
    private String identifier;

    private String employeeName;

    private String lastName;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeRole role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress")
    private Address address;

    private LocalDate birthdate;

    @ManyToOne
    @JoinColumn(name = "departmenta-id")
    private Department department;

    @ManyToMany
     @JoinTable(name="employee_mission",
     joinColumns = @JoinColumn(name="employee_id"),
     inverseJoinColumns = @JoinColumn(name="mission_id"))
    private List<Mission> missions = new ArrayList<>();
}
