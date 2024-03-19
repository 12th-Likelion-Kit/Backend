package com.likelionkit.board.domain.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.likelionkit.board.domain.user.dto.SignUpRequest;
import com.likelionkit.board.domain.user.dto.SignUpResponse;
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

@WebMvcTest(UserController.class)
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
            SignUpRequest request = new SignUpRequest("","123456");

            // when -> then
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
            SignUpRequest request = new SignUpRequest("test", password);

            // when -> then
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
}
