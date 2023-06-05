package com.nhnacademy.mini_dooray.account_api.domain.account.repository;

import com.nhnacademy.mini_dooray.account_api.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long> {

}
