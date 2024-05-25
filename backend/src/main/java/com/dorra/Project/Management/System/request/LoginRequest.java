package com.dorra.Project.Management.System.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
