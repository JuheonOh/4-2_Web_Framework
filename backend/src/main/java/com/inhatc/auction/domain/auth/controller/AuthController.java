package com.inhatc.auction.domain.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inhatc.auction.domain.auth.dto.request.AuthRequestDTO;
import com.inhatc.auction.domain.auth.dto.response.AuthResponseDTO;
import com.inhatc.auction.domain.auth.service.AuthService;
import com.inhatc.auction.domain.user.dto.request.UserRequestDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDTO requestDTO) {
        AuthResponseDTO responseDTO = this.authService.login(requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRequestDTO requestDTO) {
        this.authService.signup(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("REFRESH_TOKEN") String refreshToken) {
        this.authService.logout(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("REFRESH_TOKEN") String refreshToken) {
        String newAccessToken = this.authService.refreshToken(refreshToken);

        Map<String, String> response = new HashMap<>();
        response.put("accessToken", newAccessToken);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
