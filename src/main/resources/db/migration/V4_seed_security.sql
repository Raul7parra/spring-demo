-- 1) Roles (idempotente)
INSERT INTO roles(name) VALUES ('USER')  ON CONFLICT (name) DO NOTHING;
INSERT INTO roles(name) VALUES ('ADMIN') ON CONFLICT (name) DO NOTHING;

-- 2) Usuarios (password en BCrypt)
-- Sustituye los hashes por los tuyos (ver más abajo)
INSERT INTO users(username, password, enabled)
VALUES ('user',  '$2a$10$mGK9ufsj0bWB10cUE3gdE.LT3L4TsaycJPjVR6qzVzE7BB5dSYE.O',  TRUE)
    ON CONFLICT (username) DO NOTHING;

INSERT INTO users(username, password, enabled)
VALUES ('admin', '$2a$10$Dif6BwecrBrllY0FAz17AOThGBjbbcdgpj7avz589eaFS8HgWmhVW', TRUE)
    ON CONFLICT (username) DO NOTHING;

-- 3) Relación user_roles (idempotente por PK)
INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'USER'
WHERE u.username = 'user'
    ON CONFLICT DO NOTHING;

INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'ADMIN'
WHERE u.username = 'admin'
    ON CONFLICT DO NOTHING;