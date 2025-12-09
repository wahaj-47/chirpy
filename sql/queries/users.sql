-- name: GetUsers :many
SELECT *
FROM users
ORDER BY created_at DESC;

-- name: CreateUser :one
INSERT INTO users (email)
VALUES (
    $1
)
RETURNING *;