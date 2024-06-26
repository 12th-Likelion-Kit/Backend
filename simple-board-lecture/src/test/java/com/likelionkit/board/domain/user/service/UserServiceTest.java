package com.likelionkit.board.domain.user.service;

import com.likelionkit.board.domain.user.dto.request.LoginRequest;
import com.likelionkit.board.domain.user.dto.request.SignUpRequest;
import com.likelionkit.board.domain.user.dto.response.LoginResponse;
import com.likelionkit.board.domain.user.dto.response.SignUpResponse;
import com.likelionkit.board.domain.user.model.User;
import com.likelionkit.board.domain.user.model.UserRole;
import com.likelionkit.board.domain.user.repository.UserRepository;
import com.likelionkit.board.global.base.exception.CustomException;
import com.likelionkit.board.global.base.exception.ErrorCode;
import com.likelionkit.board.global.jwt.utils.TokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private TokenProvider tokenProvider;

    private User createUser() {
        return new User(1L,"test","123456", UserRole.ROLE_USER, null);
    }

    @Test
    void 회원가입_성공() {
        // given
        SignUpRequest request = new SignUpRequest("test","123456");
        User user = createUser();
        when(passwordEncoder.encode(anyString())).thenReturn("encrypt_password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // when
        SignUpResponse savedUserResponse = userService.signUp(request);

        // then
        assertThat(savedUserResponse.getId()).isEqualTo(user.getId());
        assertThat(savedUserResponse.getUserName()).isEqualTo(user.getUserName());
        assertThat(savedUserResponse.getRole()).isEqualTo(user.getRole());
    }

    @Test
    void 중복_유저_생성시_회원가입_실패() {
        // given
        SignUpRequest request = new SignUpRequest("test","123456");
        User user = createUser();
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));

        // when
        Throwable throwable = catchThrowable(() -> userService.signUp(request));

        // then
        assertThat(throwable)
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(ErrorCode.DUPLICATED_USER_NAME.getMessage());
    }

    @Test
    void 로그인_성공() {
        // given
        LoginRequest request = new LoginRequest("test","123456");
        User user = createUser();
        when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(),anyString())).thenReturn(true);
        when(tokenProvider.createToken(any(),any(UserRole.class))).thenReturn("ACCESS_TOKEN");

        // when
        LoginResponse response = userService.login(request);

        // then
        assertThat(response.getAccessToken()).isEqualTo("ACCESS_TOKEN");
    }
}
