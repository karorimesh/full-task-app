CREATE SEQUENCE IF NOT EXISTS task_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE user_task
(
    id          BIGINT NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    deleted_at  TIMESTAMP WITHOUT TIME ZONE,
    created_by  BIGINT,
    updated_by  BIGINT,
    deleted_by  BIGINT,
    status      VARCHAR(255),
    title       VARCHAR(255),
    description VARCHAR(255),
    priority    INTEGER,
    assignee_id BIGINT,
    CONSTRAINT pk_user_task PRIMARY KEY (id)
);