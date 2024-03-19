package com.likelionkit.board.domain.user.service;

import com.likelionkit.board.domain.user.dto.SignUpRequest;
import com.likelionkit.board.domain.user.dto.SignUpResponse;
import com.likelionkit.board.domain.user.model.User;
import com.likelionkit.board.domain.user.model.UserRole;
import com.likelionkit.board.domain.user.repository.UserRepository;
import com.likelionkit.board.global.base.exception.CustomException;
import com.likelionkit.board.global.base.exception.ErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void 회원가입_성공() {
        // given
        SignUpRequest request = new SignUpRequest("test","123456");
        User user = new User(1L,"test","123456", UserRole.USER);
        when(userRepository.save(any(User.class))).thenReturn(user);

        SignUpResponse savedUserResponse = userService.signUp(request);

        assertThat(savedUserResponse.getId()).isEqualTo(user.getId());
        assertThat(savedUserResponse.getUserName()).isEqualTo(user.getUserName());
        assertThat(savedUserResponse.getRole()).isEqualTo(user.getRole());
    }

    @Test
    void 중복_유저_생성시_회원가입_실패() {
        // given
        SignUpRequest request = new SignUpRequest("test","123456");
        User user = new User(1L,"test","123456", UserRole.USER);
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));

        // when
        Throwable throwable = catchThrowable(() -> userService.signUp(request));

        // then
        assertThat(throwable)
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(ErrorCode.DUPLICATED_USER_NAME.getMessage());
    }
}
