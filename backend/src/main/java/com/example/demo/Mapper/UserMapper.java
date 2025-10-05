package com.example.demo.Mapper;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.User;

public class UserMapper {
    public static User toUser(UserDto dto){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setRole(user.getRole());
        userDto.setUsername(user.getUsername());
        return userDto;
    }
}
