package com.example.demo.Service;

import com.example.demo.Dto.EmployeeDto;
import com.example.demo.Dto.RoleDto;
import com.example.demo.Entity.Role;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.EmployeeMapper;
import com.example.demo.Mapper.RoleMapper;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<?> createRole(RoleDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(RoleMapper.toRole(dto)));
    }

    @Override
    public ResponseEntity<RoleDto> getRoleById(Long id) {
        Role role = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("role did not found for the id " + id));
        return ResponseEntity.ok(RoleMapper.toRoleDto(role));
    }

    @Override
    public List<ResponseEntity<RoleDto>> getAllRoles() {
        return repository.findAll()
                .stream()
                .map(role -> RoleMapper.toRoleDto(role))
                .map(dto -> ResponseEntity.ok(dto))
                .toList();
    }

    @Override
    public ResponseEntity<?> updateRole(RoleDto dto, Long id) {
        Role role = repository.findById(id).orElseThrow (() -> new ResourceNotFoundException("role did not found for the id " + id));
        return ResponseEntity.ok(RoleMapper.toRoleDto(repository.save(RoleMapper.toRole(dto))));
    }

    @Override
    public ResponseEntity<Boolean> deleteRole(Long id) {
        Role role = repository.findById(id).orElseThrow (() -> new ResourceNotFoundException("role did not found for the id " + id));
        repository.delete(role);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
    }

    @Override
    public List<ResponseEntity<EmployeeDto>> getEmployeeByRoleId(Long id) {
       return employeeRepository.findByRoles_Id(id)
               .stream()
               .map(emp -> EmployeeMapper.toEmployeeDto(emp))
               .map(dto -> ResponseEntity.ok(dto))
               .toList();
    }

}
