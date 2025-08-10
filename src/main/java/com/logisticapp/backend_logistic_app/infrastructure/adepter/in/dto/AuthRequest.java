package com.logisticapp.backend_logistic_app.infrastructure.adepter.in.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuthRequest {
    @Schema(description = "User to access", example = "admin")
    private String username;

    @Schema(description = "Password to access", example = "password")
    private String password;
}
