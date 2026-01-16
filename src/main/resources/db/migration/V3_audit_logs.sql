CREATE TABLE audit_logs
(
    id         BIGSERIAL PRIMARY KEY,
    action     VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ  NOT NULL
);