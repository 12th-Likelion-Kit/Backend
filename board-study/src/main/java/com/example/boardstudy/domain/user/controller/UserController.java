package com.example.boardstudy.domain.user.controller;

import com.example.boardstudy.domain.user.dto.user.*;
import com.example.boardstudy.domain.user.entity.UserPrincipal;
import com.example.boardstudy.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(@AuthenticationPrincipal UserPrincipal user) {
        UserDto response = userService.me(user.getUserId());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> response = userService.findAll();

        return ResponseEntity.ok().body(response);
    }

    // username 수정
    @PatchMapping
    public ResponseEntity<Void> update(@AuthenticationPrincipal UserPrincipal user,
                                       @RequestBody @Valid UpdateUsernameRequest request) {
        userService.update(user.getUserId(), request.getUsername());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserPrincipal user) {
        userService.delete(user.getUserId());

        return ResponseEntity.ok().build();
    }

}
