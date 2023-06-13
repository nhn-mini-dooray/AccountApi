package com.nhnacademy.mini_dooray.account_api.domain.account.repository;

import com.nhnacademy.mini_dooray.account_api.domain.account.entity.Account;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.response.AccountLoginDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.response.EmailResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<EmailResponseDto> findByEmail(String email);
    Boolean existsByLoginId(String loginId);
    Boolean existsByEmail(String email);
    Optional<AccountLoginDto> findLoginDtoByLoginId(String loginId);
}
