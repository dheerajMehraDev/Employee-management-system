package com.example.demo.Mapper;

import com.example.demo.Dto.RoleDto;
import com.example.demo.Entity.Role;

public class RoleMapper {
    public static RoleDto toRoleDto(Role role){
        return new RoleDto(role.getRoleName());
    }
    public static Role toRole(RoleDto roleDto){
        return new Role(roleDto.getRoleName());
    }
}
