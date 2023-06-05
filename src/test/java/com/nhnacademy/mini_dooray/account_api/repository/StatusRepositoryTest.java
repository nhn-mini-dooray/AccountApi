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
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //실제데이터베이스로 실행
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@Transactional
public class StatusRepositoryTest {

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
//    @Transactional(readOnly = true)
    void findStatusById() {
        //Status returnStatus = statusRepository.findById(status.getStatusId());

        int statusId = 1;
        Status status = statusRepository.findById(statusId).orElse(null);

        assertNotNull(status);
    }

//    @Test
//    @Order(2)
//    void createStatus() {
//
//        Status status = Status.builder()
//                .name(StatusCode.ACTIVE)
//                .build();
//
//        Status savedStatus = statusRepository.save(status);
//        assertNotNull(savedStatus.getStatusId());
//        assertEquals(StatusCode.ACTIVE, savedStatus.getName());
//    }

//    @Test
//    @Order(3)
//    void updateStatusName() {
//        StatusCode statusCode = StatusCode.ACTIVE;
//        StatusCode newStatusCode = StatusCode.DORMANT;
//
//        Status status = statusRepository.findByName(statusCode).orElse(null);
//        if (status != null) {
//            status.setName(newStatusCode);
//            statusRepository.save(status);
//        }
//
//        Status updatedStatus = statusRepository.findByName(newStatusCode).orElse(null);
//
//        if (updatedStatus != null) {
//            assertEquals(newStatusCode, updatedStatus.getName());
//        }
//    }

}
