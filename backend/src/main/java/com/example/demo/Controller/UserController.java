package com.example.demo.Controller;

import com.example.demo.Dto.AuthResponse;
import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Security.Configuration.Utilities.JwtUtil;
import com.example.demo.Service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(AuthenticationManager authManager, JwtUtil jwtUtil,
                          UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser( @Valid @RequestBody UserDto userDto){
        return userService.registerUser(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id ){
        return userService.getUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User req) {
        try {
            // DEBUGGING: Check if password matches
            User userFromDb = userRepository.findByUsername(req.getUsername());

            System.out.println("Raw password from request: " + req.getPassword());
            System.out.println("Encoded password from DB: " + userFromDb.getPassword());
            System.out.println("Password matches? " + passwordEncoder.matches(req.getPassword(), userFromDb.getPassword()));

            // Now do authentication
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @DeleteMapping("/{id}")
    public Boolean removeById(@PathVariable Long id){
        userRepository.deleteById(id);
        return true;
    }
}
