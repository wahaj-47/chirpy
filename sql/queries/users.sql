-- name: GetUsers :many
SELECT *
FROM users
ORDER BY created_at DESC;

-- name: GetUser :one
SELECT *
FROM users
WHERE id = $1;

-- name: GetUserByEmail :one
SELECT *
FROM users
WHERE email = $1;

-- name: CreateUser :one
INSERT INTO users (email, hashed_password)
VALUES (
    $1,
    $2
)
RETURNING *;

-- name: ResetUsers :exec
DELETE FROM users;