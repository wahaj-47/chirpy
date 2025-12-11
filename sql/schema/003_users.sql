-- +goose Up
ALTER TABLE users
  ADD COLUMN hashed_password varchar(255) NOT NULL DEFAULT 'unset';

-- +goose Down
ALTER TABLE users
  DROP COLUMN hashed_password;