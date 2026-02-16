package com.bootdev.chirpy;

public record User(Long id, String email, String password, String createdAt) {
}
