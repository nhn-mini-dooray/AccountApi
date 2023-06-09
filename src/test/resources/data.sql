INSERT INTO `status`
(`status_name`)
VALUES
    ('ACTIVE'),
    ('DORMANT');

INSERT INTO `accounts`
(`status_id`, `account_login_id`, `account_email`, `account_password`, `account_last_login_date`)
VALUES
    (1, 'john', 'john@example.com', 'password123', now()),
    (1, 'jane', 'jane@example.com', 'password456', now()),
    (2, 'bob', 'bob@example.com', 'password789', now());



