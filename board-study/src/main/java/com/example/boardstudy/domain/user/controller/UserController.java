package com.example.boardstudy.domain.user.controller;

import com.example.boardstudy.domain.user.dto.LoginRequest;
import com.example.boardstudy.domain.user.dto.LoginResponse;
import com.example.boardstudy.domain.user.dto.SignUpRequest;
import com.example.boardstudy.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest request) {
        // 실제 회원가입
        userService.signUp(request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = userService.login(request);

        return ResponseEntity.ok().body(response);
    }
}
