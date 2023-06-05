package com.nhnacademy.mini_dooray.account_api.service;

import com.nhnacademy.mini_dooray.account_api.domain.account.entity.Account;
import com.nhnacademy.mini_dooray.account_api.domain.account.model.request.CreateAccountRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.account.repository.AccountRepository;
import com.nhnacademy.mini_dooray.account_api.domain.account.service.AccountService;
import com.nhnacademy.mini_dooray.account_api.domain.status.entity.Status;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.request.StatusCodeRequestDto;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code.StatusCode;
import com.nhnacademy.mini_dooray.account_api.domain.status.repository.StatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

//@ExtendWith(SpringExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private StatusRepository statusRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.accountService = new AccountService(accountRepository,statusRepository);//필요함?
    }

    @Test
    @DisplayName("CreateAccount 메서드 테스트")
    public void testCreateAccount() {
        // Given
        CreateAccountRequestDto requestDto = CreateAccountRequestDto.builder()
                .loginId("test")
                .password("test@example.com")
                .email("password")
                .build();

        Status status = new Status(1, StatusCode.ACTIVE);

        // when
        when(statusRepository.findByName(StatusCode.ACTIVE))
                .thenReturn(Optional.of(status));

        accountService.createAccount(requestDto);

        // then
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    @DisplayName("UpdateAccount 메서드 테스트")
    public void testUpdateAccount() {
        // given
        Long id = 1L;
        StatusCodeRequestDto statusCode = new StatusCodeRequestDto(StatusCode.ACTIVE);

        Status status = new Status(1, StatusCode.ACTIVE);
        Account account = Account.builder()
                .status(status)
                .loginId("test")
                .email("test@example.com")
                .password("password")
                .build();

        // when
        when(statusRepository.findByName(statusCode.getStatus())).thenReturn(Optional.of(status));
        when(accountRepository.findById(id)).thenReturn(Optional.ofNullable(account));

        accountService.updateAccount(id,statusCode);

        // then
        verify(accountRepository, times(1)).save(any(Account.class));
    }


    @Test
    @DisplayName("deleteAccount 메서드 테스트")
    public void deleteAccount() {
        // given
        Long id = 1L;

        // when
        accountRepository.deleteById(id);

        // then
        verify(accountRepository, times(1)).deleteById(any(Long.class));
    }
}

