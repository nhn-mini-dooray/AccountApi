package com.nhnacademy.mini_dooray.account_api.domain.account.repository;

import com.nhnacademy.mini_dooray.account_api.domain.account.entity.Account;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.response.FindByEmailResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {
    FindByEmailResponseDto findByEmail(String email);
}
