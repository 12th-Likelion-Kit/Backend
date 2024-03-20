package com.likelionkit.board.domain.user.service;

import com.likelionkit.board.domain.user.dto.request.SignUpRequest;
import com.likelionkit.board.domain.user.dto.response.SignUpResponse;
import com.likelionkit.board.domain.user.model.User;
import com.likelionkit.board.domain.user.repository.UserRepository;
import com.likelionkit.board.global.base.exception.CustomException;
import com.likelionkit.board.global.base.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        // DB에 동일한 userName 이 존재하는지 검사
        userRepository.findByUserName(request.getUserName()).ifPresent(it -> {
            // 에러 처리
            throw new CustomException(ErrorCode.DUPLICATED_USER_NAME);
        });

        // 새로운 User Entity를 만들어서 DB에 저장
        User newUser = userRepository.save(SignUpRequest.toEntity(
                request.getUserName(),
                passwordEncoder.encode(request.getPassword()))); // 비밀번호 해싱

        return SignUpResponse.fromUser(newUser);
    }
}
