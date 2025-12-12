package auth

import (
	"testing"
	"time"

	"github.com/google/uuid"
)

func TestMakeAndValidateJWT(t *testing.T) {
	secret := "secret"
	uuid := uuid.New()

	token, err := MakeJWT(uuid, secret, time.Hour)
	if err != nil {
		t.Fatalf("Error making JWT: %v", err)
	}

	userID, err := ValidateJWT(token, secret)
	if err != nil {
		t.Fatalf("Error validating JWT: %v", err)
	}

	if userID != uuid {
		t.Fatalf("User ID mismatch: expected %v, got %v", uuid, userID)
	}
}

func TestExpiredJWTRejected(t *testing.T) {
	secret := "secret"
	uuid := uuid.New()

	token, err := MakeJWT(uuid, secret, -1*time.Second)
	if err != nil {
		t.Fatalf("Error making JWT: %v", err)
	}

	_, err = ValidateJWT(token, secret)
	if err == nil {
		t.Fatalf("Expected error validating expired JWT")
	}
}

func TestInvalidJWTRejected(t *testing.T) {
	secret := "secret"
	uuid := uuid.New()

	token, err := MakeJWT(uuid, secret, time.Hour)
	if err != nil {
		t.Fatalf("Error making JWT: %v", err)
	}

	token = token[:len(token)-2] + "47"

	_, err = ValidateJWT(token, secret)
	if err == nil {
		t.Fatalf("Expected error validating invalid JWT")
	}
}
