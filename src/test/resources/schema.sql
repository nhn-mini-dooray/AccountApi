DROP TABLE IF EXISTS `accounts`;
DROP TABLE IF EXISTS `status`;

-- -----------------------------------------------------
-- Table `status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `status`
(
    `status_id`   INT         NOT NULL AUTO_INCREMENT,
    `status_name` VARCHAR(45) NULL,
    PRIMARY KEY (`status_id`)
);

-- -----------------------------------------------------
-- Table `accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accounts`
(
    `account_id`              BIGINT      NOT NULL AUTO_INCREMENT,
    `status_id`               INT         NOT NULL,
    `account_login_id`        VARCHAR(45) NOT NULL,
    `account_email`           VARCHAR(45) NOT NULL,
    `account_password`        VARCHAR(45) NOT NULL,
    `account_last_login_date` DATE        NULL,
    PRIMARY KEY (`account_id`),
        FOREIGN KEY (`status_id`)
            REFERENCES `status` (`status_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);





