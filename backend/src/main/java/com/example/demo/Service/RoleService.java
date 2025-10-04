package com.example.demo.Service;

import com.example.demo.Dto.RoleDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface RoleService {
    public ResponseEntity<?> createRole( RoleDto dto);
    public ResponseEntity<RoleDto> getRoleById( Long id);
    public List<ResponseEntity<RoleDto>> getAllRoles();
    public ResponseEntity<?> updateRole( RoleDto dto, Long id);
    public ResponseEntity<Boolean> deleteRole( Long id);



}
