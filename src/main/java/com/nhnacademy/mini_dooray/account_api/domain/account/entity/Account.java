package com.nhnacademy.mini_dooray.account_api.domain.account.entity;

import com.nhnacademy.mini_dooray.account_api.domain.status.entity.Status;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;


    @Setter
    @ManyToOne
    @NotNull
    @JoinColumn(name = "status_id")
    private Status status;


    @NotNull
    @Length(max = 45)
    @Column(name = "account_login_id")
    private String loginId;


    @NotNull
    @Length(max = 45)
    @Column(name = "account_email")
    private String email;


    @NotNull
    @Length(max = 45)
    @Column(name = "account_password")
    private String password;


    @Column(name = "account_last_login_date")
    private LocalDate lastLoginDate;


    public Account(Status status, String loginId, String email, String password) {
        this.status = status;
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.lastLoginDate = LocalDate.now();
    }

}
