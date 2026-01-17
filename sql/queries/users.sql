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

-- name: GetUserByRefreshToken :one
SELECT *
FROM users u
JOIN refresh_tokens r ON u.id = r.user_id
WHERE r.token = $1 AND r.revoked_at IS NULL AND r.expires_at > NOW();

-- name: CreateUser :one
INSERT INTO users (email, hashed_password)
VALUES (
    $1,
    $2
)
RETURNING *;

-- name: UpdateUser :one
UPDATE users
SET email = $1, hashed_password = $2
WHERE id = $3
RETURNING *;

-- name: ResetUsers :exec
DELETE FROM users;

-- name: UpgradeUser :one
UPDATE users
SET is_chirpy_red = true
WHERE id = $1
RETURNING *;