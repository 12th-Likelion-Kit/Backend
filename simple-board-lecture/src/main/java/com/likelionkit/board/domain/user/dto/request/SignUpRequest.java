package com.likelionkit.board.domain.user.dto.request;

import com.likelionkit.board.domain.user.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "사용자 이름은 빈칸일 수 없습니다.")
    private String userName;

    @NotBlank(message = "비밀번호는 빈칸일 수 없습니다.")
    @Size(min = 6, max = 12, message = "비밀번호는 6자리 이상, 12자리 이하여야 합니다.")
    private String password;

    public static User toEntity(String userName, String encodedPassword) {
        return User.builder()
                .userName(userName)
                .password(encodedPassword)
                .build();
    }
}
