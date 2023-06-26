package com.springboot.blog.controller;

import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    //Omit @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //Log in/Sign in REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }
}
