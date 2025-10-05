package com.example.demo.Controller;

import com.example.demo.Dto.DepartmentDto;
import com.example.demo.Dto.EmployeeDto;
import com.example.demo.Service.DepartmentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentServiceImpl departmentService;

    @PostMapping
    public ResponseEntity<?> createDept(@Valid @RequestBody DepartmentDto departmentDto){
        return departmentService.createDept(departmentDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDeptById(@PathVariable Long id){
        return departmentService.getDeptById(id);
    }
    @GetMapping
    public List<ResponseEntity<DepartmentDto>> getAllDepartment(){
        return departmentService.getAllDepartment();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto,@PathVariable Long id){
        return departmentService.updateDepartment(departmentDto,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable Long id){
        return departmentService.deleteDepartment(id);
    }

}
