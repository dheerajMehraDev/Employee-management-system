package com.example.demo.Controller;

import com.example.demo.Dto.EmployeeDto;
import com.example.demo.Dto.RoleDto;
import com.example.demo.Service.EmployeeService;
import com.example.demo.Service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employee){
        return employeeService.createEmployee(employee);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }
    @GetMapping
    public List<ResponseEntity<EmployeeDto>> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody EmployeeDto employee,Long id){
        return employeeService.updateEmployee(employee,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/dept/{id}")
    public List<ResponseEntity<EmployeeDto>> getEmployeeByDepartmentId(@PathVariable Long id){
        return employeeService.getEmployeeByDepartmentId(id);
    }
    @GetMapping("/role/{id}")
    public List<ResponseEntity<EmployeeDto>> getEmployeeByRoleId(@PathVariable Long id){
        return employeeService.getEmployeeByRoleId(id);
    }
}
