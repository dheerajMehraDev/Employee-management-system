package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name is blank in employee ")
    @Size(min = 2 , max = 20 , message = "name size should be in employee [2,20]")
    private String name;

    @Email(message = "email is incorrect in employee")
    @NotBlank(message = "email is blank in emploee")
    private String email;

    @NotNull(message = "department is null in employee")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_role" ,
            joinColumns = @JoinColumn( name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @NotNull(message = "role is null in employee")
    private List<Role> roles;
}
