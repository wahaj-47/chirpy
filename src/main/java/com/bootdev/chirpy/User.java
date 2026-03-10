package com.bootdev.chirpy;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users")
public record User(
        @Id UUID id,
        String email,
        @Column("hashed_password") String hashedPassword,
        @Column("created_at") Instant createdAt,
        @Column("updated_at") Instant updatedAt) {
}
