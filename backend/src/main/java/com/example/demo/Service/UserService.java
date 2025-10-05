package com.example.demo.Service;

import com.example.demo.Dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public ResponseEntity<Boolean> registerUser(UserDto userDto);
    public ResponseEntity<UserDto> getUser(Long id);
}
