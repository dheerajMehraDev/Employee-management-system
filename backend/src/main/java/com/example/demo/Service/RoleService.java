package com.example.demo.Service;

import com.example.demo.Dto.EmployeeDto;
import com.example.demo.Dto.RoleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public ResponseEntity<?> createRole( RoleDto dto);
    public ResponseEntity<RoleDto> getRoleById( Long id);
    public List<ResponseEntity<RoleDto>> getAllRoles();
    public ResponseEntity<?> updateRole( RoleDto dto, Long id);
    public ResponseEntity<Boolean> deleteRole( Long id);
    public List<ResponseEntity<EmployeeDto>> getEmployeeByRoleId(Long id );


}
