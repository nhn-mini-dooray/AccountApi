INSERT INTO `nhn_academy_42`.`status`
(`status_id`, `status_name`)
VALUES
    (1, 'ACTIVE'),
    (2, 'DORMANT');

INSERT INTO `nhn_academy_42`.`accounts`
(`account_id`, `status_id`, `account_login_id`, `account_email`, `account_password`, `account_last_login_date`)
VALUES
    (1, 1, 'john', 'john@example.com', 'password123', now()),
    (2, 1, 'jane', 'jane@example.com', 'password456', now()),
    (3, 2, 'bob', 'bob@example.com', 'password789', now());


