package com.example.ekatalogv1Server.dto;

public class PenggunaDTO {
    private String namaPengguna;
    private String passwordPengguna;
    private String rolePengguna;
    private String usernamePengguna;
    private int delFlag;

    // Getter and Setter

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getPasswordPengguna() {
        return passwordPengguna;
    }

    public void setPasswordPengguna(String passwordPengguna) {
        this.passwordPengguna = passwordPengguna;
    }

    public String getRolePengguna() {
        return rolePengguna;
    }

    public void setRolePengguna(String rolePengguna) {
        this.rolePengguna = rolePengguna;
    }

    public String getUsernamePengguna() {
        return usernamePengguna;
    }

    public void setUsernamePengguna(String usernamePengguna) {
        this.usernamePengguna = usernamePengguna;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
