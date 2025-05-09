package com.learnspringboot.springbootdatajpa.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue
    private Integer departmentId;

    private String departmentName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employeeList; // List of employees>

}
