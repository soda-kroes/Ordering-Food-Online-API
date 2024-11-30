package com.soda.response;

import com.soda.model.UserRole;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private UserRole role;
}
