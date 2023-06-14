package com.nhnacademy.mini_dooray.account_api.repository;

import com.nhnacademy.mini_dooray.account_api.domain.account.entity.Account;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.response.EmailResponseDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.repository.AccountRepository;
import com.nhnacademy.mini_dooray.account_api.domain.status.entity.Status;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code.StatusCode;
import com.nhnacademy.mini_dooray.account_api.domain.status.repository.StatusRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @Order(1)
    @Transactional(readOnly = true)
    @DisplayName("Account Select 테스트")
    void findAccountById() {

        Long accountId = 1L;
        Account account = accountRepository.findById(accountId).orElse(null);
        assert account != null;
        System.out.println(account.getLoginId() + account.getPassword());
        assertNotNull(account);
    }

    @Test
    @Order(2)
    @DisplayName("Account Create 테스트")
    void testSaveAccount() {
        // 테스트에 필요한 데이터 생성
        Status status = new Status(1, StatusCode.ACTIVE);

        status = statusRepository.save(status);

        Account account = new Account(status, "testuser", "testuser@example.com", "password");

        // 엔티티 저장
        Account savedAccount = accountRepository.save(account);

        // 저장된 엔티티 확인
        assertNotNull(savedAccount.getAccountId());
        assertEquals("testuser", savedAccount.getLoginId());
        assertEquals("testuser@example.com", savedAccount.getEmail());
        assertEquals("password", savedAccount.getPassword());
        assertEquals(LocalDate.now(), savedAccount.getLastLoginDate());
    }

    @Test
    @Order(3)
    @DisplayName("Account Update 테스트")
    void updateAccountStatus() {
        Long accountId = 1L;
        Integer newStatusId = 2;
        Status status = statusRepository.findById(newStatusId).orElse(null);

        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            account.setStatus(status);
            accountRepository.save(account);
        }

        accountRepository.findById(accountId)
                .ifPresent(updatedAccount -> assertEquals(newStatusId, updatedAccount.getStatus().getStatusId()));
    }

    @Test
    @Order(4)
    @DisplayName("Account Delete 테스트")
    void deleteAccount() {
        Long accountId = 1L;

        accountRepository.deleteById(accountId);

        Account deletedAccount = accountRepository.findById(accountId).orElse(null);
        assertNull(deletedAccount);
    }
}
