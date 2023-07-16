package com.springboot.blog.controller;

import com.springboot.blog.payload.JwtAuthResponse;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.RegisterDto;
import com.springboot.blog.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "Login and Register POST routes for Authorization - REST API"
)
public class AuthController {
    private AuthService authService;

    //Omit @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //Log in/Sign in REST API
    @Operation(
            summary = "User Login REST API",
            description = "Allows user to login.  Returns a valid token on success.  Requires entry of valid username or email, and password."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping(value = {"/login"})
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    //Register REST API
    @Operation(
            summary = "User Register REST API",
            description = "Registers user.  Returns a String message as confirmation on success.  Requires entry of valid name, username, email, and password."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping(value = {"/register"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
