package com.example.demo.Service;

import com.example.demo.Dto.EmployeeDto;
import com.example.demo.Dto.RoleDto;
import com.example.demo.Entity.Department;
import com.example.demo.Entity.Employee;
import com.example.demo.Entity.Role;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.EmployeeMapper;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for the id " + id));

        return ResponseEntity.ok(EmployeeMapper.toEmployeeDto(employee));
    }

    @Override
    public List<ResponseEntity<EmployeeDto>> getAllEmployee() {
        List<EmployeeDto> employeeList =  repository.findAll()
                .stream()
                .map(emp -> EmployeeMapper.toEmployeeDto(emp))
                .collect(Collectors.toList());

       return employeeList.stream().map(emp -> ResponseEntity.ok(emp))
               .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        Department department = employee.getDepartment();
        departmentRepository.save(department);
        List<Role> roles = employee.getRoles();
        for(Role role : roles) roleRepository.save(role);
        Employee save = repository.save(employee);
        return ResponseEntity.ok(save);
    }

    @Override
    public ResponseEntity<?> updateEmployee(EmployeeDto employeeDto,Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee not found for the id " +id));
        return ResponseEntity.ok(repository.save(EmployeeMapper.toEmployee(employeeDto)));
    }

    @Override
    public ResponseEntity<?> deleteEmployee(Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee not found for the id " + id));
        repository.delete(employee);
       return
               ResponseEntity.ok(Boolean.TRUE);
    }
}
