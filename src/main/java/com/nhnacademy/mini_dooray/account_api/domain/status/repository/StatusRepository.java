package com.nhnacademy.mini_dooray.account_api.domain.status.repository;

import com.nhnacademy.mini_dooray.account_api.domain.status.entity.Status;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Optional<Status> findByName(StatusCode statusCode);
}
