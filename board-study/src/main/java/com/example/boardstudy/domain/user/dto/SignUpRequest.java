package com.example.boardstudy.domain.user.dto;

import com.example.boardstudy.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank(message = "사용자 이름은 빈값일 수 없습니다.")
    private String username;

    @NotBlank(message = "비밀번호는 빈값일 수 없습니다.")
    @Size(min = 4, max = 12, message = "비밀번호는 4자리 이상 12자리 이하여야 합니다.")
    private String password;

    public static User toEntity(SignUpRequest request) {
        return User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }
}
