package com.bosonit.formacion.security.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String usuario;
    private String password;
    private boolean admin;
}
