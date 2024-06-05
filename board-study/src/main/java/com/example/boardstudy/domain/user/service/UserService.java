package com.example.boardstudy.domain.user.service;

import com.example.boardstudy.domain.user.dto.user.LoginRequest;
import com.example.boardstudy.domain.user.dto.user.LoginResponse;
import com.example.boardstudy.domain.user.dto.user.SignUpRequest;
import com.example.boardstudy.domain.user.dto.user.UserDto;
import com.example.boardstudy.domain.user.entity.User;
import com.example.boardstudy.domain.user.repository.UserRepository;
import com.example.boardstudy.global.base.exception.CustomException;
import com.example.boardstudy.global.base.exception.ErrorCode;
import com.example.boardstudy.global.jwt.TokenProvider;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpRequest request) {
        // username 중복 확인
        userRepository.findByUsername(request.getUsername())
                .ifPresent(it -> {
                    throw new CustomException(ErrorCode.DUPLICATED_USERNAME);
                });

        // 실제 DB 저장
        User newUser = SignUpRequest.toEntity(request.getUsername(), passwordEncoder.encode(request.getPassword()));
        userRepository.save(newUser);
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        // DB에 해당 회원이 존재하는지 검사
        User savedUser = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND)); //404 NOT FOUND

        // 비밀번호 검사
        if(!passwordEncoder.matches(request.getPassword(), savedUser.getPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_INVALID); // 400
        }

        // 토큰을 발급
        String token = tokenProvider.createToken(savedUser.getUsername(), savedUser.getRole());
        return new LoginResponse(token);
    }

    public UserDto me(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserDto(user);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::new)
                .toList();
    }

    @Transactional
    public void update(Long userId, String newUsername) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        user.setUsername(newUsername);
    }

    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
