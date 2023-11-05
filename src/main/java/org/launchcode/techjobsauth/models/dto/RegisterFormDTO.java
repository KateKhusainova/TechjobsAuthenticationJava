package org.launchcode.techjobsauth.models.dto;

// 2.3. Create two DTOs for the user registration and login forms in a new package, dto, under models.
// b) Create a register form DTO with the fields above and a field to verify a password.

public class RegisterFormDTO extends LoginFormDTO {

    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

}
