package com.likelionkit.board.domain.user.dto;

import com.likelionkit.board.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank(message = "사용자 이름은 빈칸일 수 없습니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 빈칸일 수 없습니다.")
    private String password;

    public static User toEntity(SignUpRequest request) {
        return User.builder()
                .userName(request.userName)
                .password(request.password)
                .build();
    }
}
