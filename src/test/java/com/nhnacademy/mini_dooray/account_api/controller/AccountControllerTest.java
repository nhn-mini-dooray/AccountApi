package com.nhnacademy.mini_dooray.account_api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.mini_dooray.account_api.domain.account.controller.RestAccountController;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.CreateAccountRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.EmailRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.response.FindByEmailResponseDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.service.AccountService;
import com.nhnacademy.mini_dooray.account_api.domain.status.entity.Status;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.request.StatusCodeRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code.StatusCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestAccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AccountService accountService;
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("signup 테스트")
    void signupTest() throws Exception {

        CreateAccountRequestDto requestDto = CreateAccountRequestDto.builder()
                .loginId("test")
                .password("password")
                .email("test@example.com")
                .build();

        doNothing().when(accountService).createAccount(requestDto);

        mvc.perform(post("/accounts/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("dormant 테스트")
    void dormantTest() throws Exception {
        Long accountId = 1L;
        StatusCodeRequestDto statusDto = new StatusCodeRequestDto(StatusCode.ACTIVE);

        doNothing().when(accountService).updateAccount(accountId, statusDto);

        mvc.perform(put("/accounts/" + accountId.intValue() + "/dormant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(statusDto)))
                .andExpect(status().isNoContent());

    }

    @Test
    @DisplayName("withdrawal 테스트")
    void withdrawalTest() throws Exception {
        Long accountId = 1L;
        doNothing().when(accountService).deleteAccount(accountId);

        mvc.perform(delete("/accounts/" + accountId.intValue() + "/withdrawal"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("email 테스트")
    void emailTest() throws Exception {
        EmailRequestDto emailRequestDto = new EmailRequestDto("jane@example.com");

        FindByEmailResponseDto findByEmailResponseDto = new FindByEmailResponseDto() {
            @Override
            public Long getAccountId() {
                return 2L;
            }

            @Override
            public Status getStatus() {
                return new Status(1, StatusCode.ACTIVE);
            }

            @Override
            public String getLoginId() {
                return "jane";
            }

            @Override
            public String getPassword() {
                return "password456";
            }

            @Override
            public LocalDate getLastLoginDate() {
                return LocalDate.parse("2023-06-06");
            }
        };

        given(accountService.findAccountByEmail(any(EmailRequestDto.class))).willReturn(findByEmailResponseDto);

        mvc.perform(post("/accounts/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emailRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId", equalTo(2)))
                .andExpect(jsonPath("$.password", equalTo("password456")))
                .andExpect(jsonPath("$.lastLoginDate", equalTo("2023-06-06")))
                .andExpect(jsonPath("$.status.statusId", equalTo(1)))
                .andExpect(jsonPath("$.status.name", equalTo("ACTIVE")))
                .andExpect(jsonPath("$.loginId", equalTo("jane")));
    }

}
