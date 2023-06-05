package com.nhnacademy.mini_dooray.account_api.domain.status.model.request;

import com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatusCodeRequestDto {
    private StatusCode status;
}
