package com.nhnacademy.mini_dooray.account_api.domain.account.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class EmailRequestDto {
    @NotNull
    String email;
}
