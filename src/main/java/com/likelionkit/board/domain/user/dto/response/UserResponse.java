package com.likelionkit.board.domain.user.dto.response;

import com.likelionkit.board.domain.user.model.User;
import com.likelionkit.board.domain.user.model.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserResponse(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
}
