package com.likelionkit.board.domain.user.repository;

import com.likelionkit.board.domain.user.model.User;
import com.likelionkit.board.domain.user.model.UserRole;
import com.likelionkit.board.global.config.JpaConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(JpaConfig.class)
@DataJpaTest // 기본 H2-DB, 테스트가 끝나면 트랜잭션 롤백해줌.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB를 사용하도록 설정
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void BaseEntity_테스트() {
        // given
        User user = new User(1L,"test","123456", UserRole.USER);

        // when
        User savedUser = userRepository.save(user);

        // then
        LocalDateTime createdAt = savedUser.getCreatedAt();
        LocalDateTime updatedAt = savedUser.getUpdatedAt();
        assertThat(savedUser.getDeletedAt()).isNull();
        assertThat(createdAt).isNotNull();
        assertThat(updatedAt).isNotNull();
    }

    @Test
    void findByUserName_테스트() {
        // given
        User user = new User(1L,"test","123456", UserRole.USER);

        // when
        User savedUser = userRepository.save(user);

        // then
        assertNotNull(savedUser);
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getUserName(), savedUser.getUserName());
        assertEquals(user.getRole(), savedUser.getRole());
    }
}