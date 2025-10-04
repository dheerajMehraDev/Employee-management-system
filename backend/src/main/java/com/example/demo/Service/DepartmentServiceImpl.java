package com.example.demo.Service;

import com.example.demo.Dto.DepartmentDto;
import com.example.demo.Entity.Department;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.DepartmentMapper;
import com.example.demo.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository repository;

    @Override
    public ResponseEntity<?> createDept(DepartmentDto departmentDto) {
       Department department =  repository.save(DepartmentMapper.toDepartment(departmentDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(DepartmentMapper.toDepartmentDto(department));
    }

    @Override
    public ResponseEntity<DepartmentDto> getDeptById(Long id) {
        Department department = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("department did not found for the id " + id));
        return ResponseEntity.ok(DepartmentMapper.toDepartmentDto(department));
    }

    @Override
    public List<ResponseEntity<DepartmentDto>> getAllDepartment() {
        return repository.findAll()
                .stream().map(dept -> DepartmentMapper.toDepartmentDto(dept))
                .map(dto -> ResponseEntity.ok(dto))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<?> updateDepartment(DepartmentDto departmentDto, Long id) {
        Department department = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("department did not found for the id " + id));
        return ResponseEntity.ok(DepartmentMapper.toDepartmentDto(repository.save(DepartmentMapper.toDepartment(departmentDto))));
    }

    @Override
    public ResponseEntity<Boolean> deleteDepartment(Long id) {
        Department department = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("department did not found for the id " + id));
        repository.delete(department);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
    }
}
