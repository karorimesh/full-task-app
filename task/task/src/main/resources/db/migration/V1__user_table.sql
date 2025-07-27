CREATE SEQUENCE IF NOT EXISTS user_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE app_user
(
    id         BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    created_by BIGINT,
    updated_by BIGINT,
    deleted_by BIGINT,
    status     VARCHAR(255),
    username   VARCHAR(255),
    email      VARCHAR(255),
    password   VARCHAR(255),
    role       VARCHAR(255),
    CONSTRAINT pk_app_user PRIMARY KEY (id)
);