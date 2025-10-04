package com.example.demo.Service;

import com.example.demo.Dto.EmployeeDto;
import com.example.demo.Dto.RoleDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface EmployeeService {

    public ResponseEntity<EmployeeDto> getEmployeeById(Long id);
    public List<ResponseEntity<EmployeeDto>> getAllEmployee();
    public ResponseEntity<?> createEmployee( EmployeeDto employee);
    public ResponseEntity<?> updateEmployee( EmployeeDto employee,Long id);
    public ResponseEntity<?> deleteEmployee(Long id);
}
