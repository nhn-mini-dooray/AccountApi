package com.nhnacademy.mini_dooray.account_api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.mini_dooray.account_api.domain.account.controller.RestAccountController;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.CreateAccountRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.service.AccountService;
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

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestAccountController.class)
public class AccountControllerTest {

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

}
