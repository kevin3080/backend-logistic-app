package com.logisticapp.backend_logistic_app.infrastructure.security;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_users") // Renamed to avoid conflict with 'user' keyword in some DBs
public class User {
    @Id
    @UuidGenerator
    private UUID id;

    private String username;
    private String password; // This should be encoded
    private String roles; // e.g., "ROLE_USER,ROLE_ADMIN"
}
