package com.example.duanappdocsach.objec.objec;

import org.json.JSONException;
import org.json.JSONObject;

public class ChuongSach {
    private String tenChuong,ngayDang;
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
    public ChuongSach(String tenChuong, String ngayDang) {
        this.tenChuong = tenChuong;
        this.ngayDang = ngayDang;
    }
    public ChuongSach(JSONObject o) throws JSONException
    {
        tenChuong = o.getString("tenChuong");
        ngayDang = o.getString("ngayNhap");
    }
}
