package com.nhnacademy.mini_dooray.account_api.repository;

import com.nhnacademy.mini_dooray.account_api.domain.status.entity.Status;
import com.nhnacademy.mini_dooray.account_api.domain.status.model.type_code.StatusCode;
import com.nhnacademy.mini_dooray.account_api.domain.status.repository.StatusRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StatusRepositoryTest {

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    TestEntityManager testEntityManager;
    Status status;

    @BeforeEach
    void setUp() {
        status = new Status(null, StatusCode.ACTIVE);
        testEntityManager.persist(status);
    }

    @Test
    @Order(1)
    void findStatusById() {
        int statusId = 1;
        Status status = statusRepository.findById(statusId).orElse(null);

        assertNotNull(status);
    }

}
