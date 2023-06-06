DROP TABLE IF EXISTS `nhn_academy_42`.`accounts`;
DROP TABLE IF EXISTS `nhn_academy_42`.`status`;


CREATE SCHEMA IF NOT EXISTS `nhn_academy_42` DEFAULT CHARACTER SET utf8;
USE `nhn_academy_42` ;

-- -----------------------------------------------------
-- Table `mini_dooray_task`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_42`.`status`
(
    `status_id`   INT         NOT NULL AUTO_INCREMENT,
    `status_name` VARCHAR(45) NULL unique,
    PRIMARY KEY (`status_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mini_dooray_task`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `nhn_academy_42`.`accounts`
(
    `account_id`              BIGINT      NOT NULL AUTO_INCREMENT,
    `status_id`               INT         NOT NULL,
    `account_login_id`        VARCHAR(45) NOT NULL unique,
    `account_email`           VARCHAR(45) NOT NULL unique,
    `account_password`        VARCHAR(45) NOT NULL,
    `account_last_login_date` DATE        NULL,
    PRIMARY KEY (`account_id`),
    INDEX                     `fk_account_status1_idx` (`status_id` ASC) VISIBLE,
    UNIQUE INDEX `account_login_id_UNIQUE` (`account_login_id` ASC) VISIBLE,
    CONSTRAINT `fk_account_status1`
        FOREIGN KEY (`status_id`)
            REFERENCES `nhn_academy_42`.`status` (`status_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;