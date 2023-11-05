package org.launchcode.techjobsauth.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 1.1. In the project you have cloned, create a User model. The class needs:
// a) To be an entity.
// b) To have username and encrypted password fields.
// c)To have appropriate constructors, getters, setters.

@Entity
public class User extends AbstractEntity {
    @NotNull
    private String username;

    @NotNull
    private String pwHash;

// 1.2. Encode the User password field.
// a) Add a static BCryptPasswordEncoder variable.
// b) Update the constructor that has arguments to encode the password field.
// c) Add a method to check password values.

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}
