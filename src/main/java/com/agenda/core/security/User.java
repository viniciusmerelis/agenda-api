package com.agenda.core.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
