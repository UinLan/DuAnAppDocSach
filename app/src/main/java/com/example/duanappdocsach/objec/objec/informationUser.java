package com.example.duanappdocsach.objec.objec;

import java.io.Serializable;

public class informationUser implements Serializable {
    private String email;
    public informationUser(){
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
