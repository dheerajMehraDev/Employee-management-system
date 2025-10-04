package com.example.demo.Mapper;

import com.example.demo.Dto.DepartmentDto;
import com.example.demo.Entity.Department;

public class DepartmentMapper {

    public static Department toDepartment(DepartmentDto departmentDto){
        Department department  = new Department();
        department.setName(departmentDto.getName());
        department.setLocation(departmentDto.getLocation());
        return department;
    }

    public static DepartmentDto toDepartmentDto(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(department.getName());
        departmentDto.setLocation(department.getLocation());
        return departmentDto;
    }

}
