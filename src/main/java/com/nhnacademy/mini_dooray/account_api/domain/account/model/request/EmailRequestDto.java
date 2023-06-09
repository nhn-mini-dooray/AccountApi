package com.nhnacademy.mini_dooray.account_api.domain.account.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDto {
    @NotNull
    String email;
}
