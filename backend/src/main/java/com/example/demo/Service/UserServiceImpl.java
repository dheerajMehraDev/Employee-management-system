package com.example.demo.Service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Boolean> registerUser(UserDto dto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(UserMapper.toUser(dto));
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Override
    public ResponseEntity<UserDto> getUser(Long id) {
      User user =  userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("user not found for the id " + id));
      return ResponseEntity.ok(UserMapper.toUserDto(user));

    }
}
