package com.likelionkit.board.domain.user.dto;

import com.likelionkit.board.domain.user.model.User;
import com.likelionkit.board.domain.user.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpResponse {
    private Long id;
    private String userName;
    private UserRole role;

    public static SignUpResponse fromUser(User user) {
        return new SignUpResponse(
                user.getId(),
                user.getUserName(),
                user.getRole()
        );
    }
}
