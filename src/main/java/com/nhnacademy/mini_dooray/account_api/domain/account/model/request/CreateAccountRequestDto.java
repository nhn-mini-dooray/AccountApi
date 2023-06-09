package com.nhnacademy.mini_dooray.account_api.domain.account.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateAccountRequestDto {
    private String loginId;
    private String email;
    private String password;

}
