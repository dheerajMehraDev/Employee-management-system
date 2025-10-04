package com.example.demo.Controller;

import com.example.demo.Dto.DepartmentDto;
import com.example.demo.Dto.RoleDto;
import com.example.demo.Service.RoleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {


    @Autowired
    RoleServiceImpl roleService;

    @PostMapping
    public ResponseEntity<?> createRole(@Valid @RequestBody RoleDto dto){
        return roleService.createRole(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id){
        return roleService.getRoleById(id);
    }
    @GetMapping
    public List<ResponseEntity<RoleDto>> getAllRoles(){
        return roleService.getAllRoles();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@Valid @RequestBody RoleDto dto,@PathVariable Long id){
        return roleService.updateRole(dto,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable Long id){
        return roleService.deleteRole(id);
    }

}
