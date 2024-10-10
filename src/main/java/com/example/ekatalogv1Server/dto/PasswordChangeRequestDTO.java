package com.example.ekatalogv1Server.dto;

public class PasswordChangeRequestDTO {
    
    private String passwordLama;
    private String passwordBaru;
    private String konfirmasiPassword;

    public String getPasswordLama() {
        return passwordLama;
    }

    public void setPasswordLama(String passwordLama) {
        this.passwordLama = passwordLama;
    }

    public String getPasswordBaru() {
        return passwordBaru;
    }

    public void setPasswordBaru(String passwordBaru) {
        this.passwordBaru = passwordBaru;
    }

    public String getKonfirmasiPassword() {
        return konfirmasiPassword;
    }

    public void setKonfirmasiPassword(String konfirmasiPassword) {
        this.konfirmasiPassword = konfirmasiPassword;
    }
}
