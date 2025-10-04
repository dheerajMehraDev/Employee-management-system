package com.example.demo.Service;

import com.example.demo.Dto.DepartmentDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface DepartmentService {
    public ResponseEntity<?> createDept(DepartmentDto departmentDto);
    public ResponseEntity<DepartmentDto> getDeptById( Long id);
    public List<ResponseEntity<DepartmentDto>> getAllDepartment();
    public ResponseEntity<?> updateDepartment( DepartmentDto departmentDto,Long id);
    public ResponseEntity<?> deleteDepartment( Long id);


}
