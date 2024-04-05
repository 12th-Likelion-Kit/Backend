package com.likelionkit.board.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelionkit.board.domain.user.dto.request.LoginRequest;
import com.likelionkit.board.domain.user.dto.request.SignUpRequest;
import com.likelionkit.board.domain.user.dto.response.LoginResponse;
import com.likelionkit.board.domain.user.dto.response.SignUpResponse;
import com.likelionkit.board.domain.user.model.UserRole;
import com.likelionkit.board.domain.user.service.UserService;
import com.likelionkit.board.global.base.exception.CustomException;
import com.likelionkit.board.global.base.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = {UserController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Nested
    @DisplayName("<회원가입>")
    class signUpTest {
        @DisplayName("회원가입이 성공함.")
        @Test
        void 회원가입_성공() throws Exception {
            // given
            SignUpRequest request = new SignUpRequest("test","123456");
            SignUpResponse response = new SignUpResponse(1L,"test", UserRole.USER);
            given(userService.signUp(any(SignUpRequest.class))).willReturn(response);

            //when -> then
            mockMvc.perform(post("/api/users/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    //then
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id").value(1L))
                    .andExpect(jsonPath("$.userName").value("test"))
                    .andExpect(jsonPath("$.role").value("USER"))
                    .andDo(print());
        }

        @Test
        void 회원가입시_이미_존재하는_userName이면_실패() throws Exception{
            // given
            SignUpRequest request = new SignUpRequest("test","123456");
            doThrow(new CustomException(ErrorCode.DUPLICATED_USER_NAME))
                    .when(userService).signUp(any(SignUpRequest.class));

            // when -> then
            mockMvc.perform(post("/api/users/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    //then
                    .andExpect(status().isConflict());
        }

        @Test
        void 회원가입시_빈값이_들어오면_실패() throws Exception{
            // given
            SignUpRequest request = new SignUpRequest("","123456");

            // when
            mockMvc.perform(post("/api/users/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    //then
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                    .andExpect(jsonPath("$.message").value("사용자 이름은 빈칸일 수 없습니다."));
        }

        @ValueSource(strings = {"12","12345","1234567891011"})
        @ParameterizedTest
        void 회원가입시_비밀번호_길이가_6보다작거나_12보다크면_실패(String password) throws Exception{
            // given
            SignUpRequest request = new SignUpRequest("test", password);

            // when
            mockMvc.perform(post("/api/users/sign-up")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    //then
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                    .andExpect(jsonPath("$.message").value("비밀번호는 6자리 이상, 12자리 이하여야 합니다."))
                    .andDo(print());
        }
    }

    @Nested
    @DisplayName("<로그인>")
    class loginTest {
        String loginUrl = "/api/users/login";
        LoginRequest request = new LoginRequest("test","123456");
        LoginResponse response = new LoginResponse("ACCESS_TOKEN");

        @Test
        void 로그인_성공() throws Exception{
            //  given
            given(userService.login(any(LoginRequest.class))).willReturn(response);

            // when
            mockMvc.perform(post(loginUrl)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    // then
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.accessToken").value("ACCESS_TOKEN"))
                    .andDo(print());
        }

        @Test
        void 회원가입되지_않은_유저라면_로그인_실패() throws Exception{
            // given
            doThrow(new CustomException(ErrorCode.NOT_FOUND_USER)).when(userService).login(any(LoginRequest.class));

            // when
            mockMvc.perform(post(loginUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    // then
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.status").value("NOT_FOUND"))
                    .andExpect(jsonPath("$.message").value("해당 Username 을 찾을 수 없습니다."))
                    .andDo(print());
        }

        @Test
        void 비밀번호가_일치하지_않는다면_로그인_실패() throws Exception{
            // given
            doThrow(new CustomException(ErrorCode.MISMATCH_USER_PASSWORD)).when(userService).login(any(LoginRequest.class));

            // when
            mockMvc.perform(post(loginUrl)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    // then
                    .andExpect(status().isUnauthorized())
                    .andExpect(jsonPath("$.status").value("UNAUTHORIZED"))
                    .andExpect(jsonPath("$.message").value("비밀번호가 일치하지 않습니다."))
                    .andDo(print());
        }
    }
}
