package com.nhnacademy.mini_dooray.account_api.domain.status.entity;


import com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code.StatusCode;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer statusId;

    @NotNull
    @Length(max = 45)
    @Enumerated(EnumType.STRING)
    @Column(name = "status_name")
    private StatusCode name = StatusCode.ACTIVE;


}
