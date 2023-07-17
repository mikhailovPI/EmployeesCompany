drop table users_roles cascade;

drop table roles cascade;

drop table users cascade;

CREATE TABLE IF NOT EXISTS roles
(
    role_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    role_name VARCHAR(32)                             NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS users
(
    user_id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_last_name  VARCHAR(256)                            NOT NULL,
    user_first_name VARCHAR(256)                            NOT NULL,
    user_patronymic VARCHAR(256)                            ,
    --user_birthdate  DATE                              NOT NULL,
    user_birthdate  VARCHAR(48)                             NOT NULL,
    email           VARCHAR(256)                            NOT NULL,
    password        VARCHAR(256)                            NOT NULL,
    phone_number    VARCHAR(128)                            NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_role_id_users_roles FOREIGN KEY (role_id) REFERENCES roles (role_id),
    CONSTRAINT fk_user_id_users_roles FOREIGN KEY (user_id) REFERENCES users (user_id)
);