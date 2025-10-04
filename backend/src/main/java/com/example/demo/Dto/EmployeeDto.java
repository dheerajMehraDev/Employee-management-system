package com.example.demo.Dto;

import com.example.demo.Entity.Department;
import com.example.demo.Entity.Role;
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

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @NotBlank(message = "name is blank in employee ")
    @Size(min = 2 , max = 20 , message = "name size should be in employee [2,20]")
    private String name;

    @Email(message = "email is incorrect in employee")
    @NotBlank(message = "email is blank in emploee")
    private String email;

    @NotNull(message = "department is null in employee")
    private Department department;


    @NotNull(message = "role is null in employee")
    private List<RoleDto> roles;
}
