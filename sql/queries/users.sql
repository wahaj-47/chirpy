-- name: GetUsers :many
SELECT *
FROM users
ORDER BY created_at DESC;

-- name: GetUser :one
SELECT *
FROM users
WHERE id = $1;

-- name: CreateUser :one
INSERT INTO users (email)
VALUES (
    $1
)
RETURNING *;

-- name: ResetUsers :exec
DELETE FROM users;