package com.example.boardstudy.domain.user.dto.user;

import com.example.boardstudy.domain.user.entity.User;
import com.example.boardstudy.domain.user.entity.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private UserRole role;

    public UserDto(User user ) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.role = user.getRole();
    }
}
