package com.likelionkit.board.domain.user.service;

import com.likelionkit.board.domain.user.dto.SignUpRequest;
import com.likelionkit.board.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public void signUp(SignUpRequest request) {
        // DB에 동일한 userName 이 존재하는지 검사
        userRepository.findByUserName(request.getUserName()).ifPresent(it -> {
            // 에러 처리
        });

        // 새로운 User Entity를 만들어서 DB에 저장
        userRepository.save(SignUpRequest.toEntity(request));
    }
}
