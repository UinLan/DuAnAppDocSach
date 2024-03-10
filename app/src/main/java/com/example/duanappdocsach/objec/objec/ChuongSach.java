package com.example.duanappdocsach.objec.objec;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class ChuongSach implements Serializable {
    private String tenChuong,ngayDang,id;
    public ChuongSach(){
    }
    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChuongSach(String tenChuong, String ngayDang) {
        this.tenChuong = tenChuong;
        this.ngayDang = ngayDang;
    }
    public ChuongSach(JSONObject o) throws JSONException
    {
        tenChuong = o.getString("tenChuong");
        ngayDang = o.getString("ngayNhap");
        id = o.getString("id");
    }
}
