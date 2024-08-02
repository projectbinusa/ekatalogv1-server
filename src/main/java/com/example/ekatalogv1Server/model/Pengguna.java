package com.example.ekatalogv1Server.model;

import com.example.ekatalogv1Server.auditing.DateConfig;

import javax.persistence.*;

@Entity
@Table(name = "tabel_pengguna")
public class Pengguna extends DateConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pengguna")
    private Long IdPengguna;

    @Column(name = "nama_pengguna", nullable = false, length = 255)
    private String namaPengguna;

    @Column(name = "password_pengguna", nullable = false, length = 255)
    private String passwordPengguna;

    @Column(name = "role_pengguna", nullable = false, length = 255)
    private String rolePengguna;

    @Column(name = "username_pengguna", nullable = false, length = 255)
    private String usernamePengguna;

    // Getter Setter

    public Long getIdPengguna() {
        return IdPengguna;
    }

    public void setIdPengguna(Long idPengguna) {
        IdPengguna = idPengguna;
    }

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
}
