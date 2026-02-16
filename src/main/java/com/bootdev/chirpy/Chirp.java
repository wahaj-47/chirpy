package com.bootdev.chirpy;

public record Chirp(Long id, Long userId, String body, String createdAt) {
}
