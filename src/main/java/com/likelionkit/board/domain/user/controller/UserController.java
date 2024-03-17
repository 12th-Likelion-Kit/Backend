package com.likelionkit.board.domain.user.controller;

import com.likelionkit.board.domain.user.dto.SignUpRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpRequest request) {
        // 회원가입 로직

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .build();
    }
}
