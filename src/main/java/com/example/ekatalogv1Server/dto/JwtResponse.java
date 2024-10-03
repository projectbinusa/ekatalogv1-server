package com.example.ekatalogv1Server.dto;

import com.example.ekatalogv1Server.model.Pengguna;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final Pengguna userData;

    public JwtResponse(String jwttoken, Pengguna userData) {
        this.jwttoken = jwttoken;
        this.userData = userData;
    }

    public Pengguna getUserData() {return userData; }

    public String getToken() {
        return this.jwttoken;
    }
}
