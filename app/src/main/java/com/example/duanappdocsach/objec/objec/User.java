package com.example.duanappdocsach.objec.objec;

import java.io.Serializable;

public class User implements Serializable {
    private String Hoten;
    private String Email;

    private String SoDienThoai;
    private String NgaySinh;
    private  String NoiSinhSong;

    public User(String hoten, String email, String soDienThoai, String ngaySinh, String noiSinhSong) {
        Hoten = hoten;
        Email = email;
        SoDienThoai = soDienThoai;
        NgaySinh = ngaySinh;
        NoiSinhSong = noiSinhSong;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getNoiSinhSong() {
        return NoiSinhSong;
    }

    public void setNoiSinhSong(String noiSinhSong) {
        NoiSinhSong = noiSinhSong;
    }
}