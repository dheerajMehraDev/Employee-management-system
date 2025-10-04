package com.example.demo.Mapper;

import com.example.demo.Dto.EmployeeDto;
import com.example.demo.Dto.RoleDto;
import com.example.demo.Entity.Employee;
import com.example.demo.Entity.Role;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {


    public static EmployeeDto toEmployeeDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setName(employee.getName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartment(employee.getDepartment());
        List<Role> roles = employee.getRoles();
        List<RoleDto> roleDtos = roles.stream().map(role -> RoleMapper.toRoleDto(role))
                        .collect(Collectors.toList());
       employeeDto.setRoles(roleDtos);
       return employeeDto;
    }

    public static Employee toEmployee(EmployeeDto employeeDto){
        Employee employee1 = new Employee();
        employee1.setName(employeeDto.getName());
        employee1.setEmail(employeeDto.getEmail());
        employee1.setDepartment(employeeDto.getDepartment());
        List<RoleDto> rolesDto = employeeDto.getRoles();
        List<Role> roles = rolesDto.stream()
                        .map(roleDto -> RoleMapper.toRole(roleDto))
                                .collect(Collectors.toList());
        employee1.setRoles(roles);
        return employee1;
    }


}
