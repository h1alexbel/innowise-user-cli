CREATE SCHEMA user_cli;

CREATE TABLE user_cli.user_data
(
    id         BIGSERIAL PRIMARY KEY,
    first_name CHARACTER VARYING(128)        NOT NULL,
    last_name  CHARACTER VARYING(128)        NOT NULL,
    email      CHARACTER VARYING(256) UNIQUE NOT NULL
);

CREATE TABLE user_cli.phone_number
(
    id      BIGSERIAL PRIMARY KEY,
    number  CHARACTER VARYING(13) NOT NULL,
    user_id BIGINT REFERENCES user_cli.user_storage (id)
);

CREATE TABLE user_cli.role
(
    id   BIGSERIAL PRIMARY KEY,
    role CHARACTER VARYING(16) UNIQUE NOT NULL
);

CREATE TABLE user_cli.user_roles
(
    user_id BIGINT REFERENCES user_cli.user_storage (id),
    role_id BIGINT REFERENCES user_cli.role_storage (id),
    PRIMARY KEY (user_id, role_id)
);