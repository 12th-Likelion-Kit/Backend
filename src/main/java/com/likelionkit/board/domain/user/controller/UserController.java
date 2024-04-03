package com.likelionkit.board.domain.user.controller;

import com.likelionkit.board.domain.user.dto.request.UserUpdateRequest;
import com.likelionkit.board.domain.user.dto.response.UserResponse;
import com.likelionkit.board.domain.user.dto.request.LoginRequest;
import com.likelionkit.board.domain.user.dto.request.SignUpRequest;
import com.likelionkit.board.domain.user.dto.response.LoginResponse;
import com.likelionkit.board.domain.user.dto.response.SignUpResponse;
import com.likelionkit.board.domain.user.model.UserPrincipal;
import com.likelionkit.board.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        // 회원가입 로직
        SignUpResponse response = userService.signUp(request);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = userService.login(request);

        return ResponseEntity
                .status(HttpStatus.OK) // 200
                .body(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal UserPrincipal user) {
        UserResponse response = userService.me(user);

        return ResponseEntity
                .status(HttpStatus.OK) // 200
                .body(response);
    }

    @PatchMapping
    public ResponseEntity<UserResponse> update(@AuthenticationPrincipal UserPrincipal user,
                                               @RequestBody UserUpdateRequest request) {
        UserResponse response = userService.update(user.getUserId(), request);

        return ResponseEntity
                .status(HttpStatus.OK) // 200
                .body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserPrincipal user) {
        userService.delete(user.getUserId());

        return ResponseEntity
                .status(HttpStatus.OK) // 200
                .build();
    }
}
