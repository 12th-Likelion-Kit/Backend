package com.likelionkit.board.domain.user.controller;

import com.likelionkit.board.domain.user.dto.SignUpRequest;
import com.likelionkit.board.domain.user.dto.SignUpResponse;
import com.likelionkit.board.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
