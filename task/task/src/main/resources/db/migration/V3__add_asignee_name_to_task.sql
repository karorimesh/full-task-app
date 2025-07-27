ALTER TABLE user_task ADD  COLUMN IF NOT EXISTS assignee_name VARCHAR(100);
CREATE UNIQUE INDEX IF NOT EXISTS unique_user_name ON app_user(username);
CREATE UNIQUE INDEX IF NOT EXISTS unique_email ON app_user(email);