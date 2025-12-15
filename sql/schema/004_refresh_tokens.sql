-- +goose Up
CREATE TABLE refresh_tokens (
  token TEXT PRIMARY KEY,
  user_id uuid NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  created_at timestamptz NOT NULL DEFAULT now(),
  updated_at timestamptz NOT NULL DEFAULT now(),
  expires_at timestamptz NOT NULL DEFAULT now() + '60 days'::interval,
  revoked_at timestamptz DEFAULT NULL
);

-- +goose Down
DROP TABLE refresh_tokens;