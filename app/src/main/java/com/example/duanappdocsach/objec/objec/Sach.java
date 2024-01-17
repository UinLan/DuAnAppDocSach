package com.example.duanappdocsach.objec.objec;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Sach implements Serializable {
    private String tenSach,linkAnh,id;
    /*
    {
    "tenSach":"",
    "linkAnh":""
    }
     */
    public Sach()
    {}
    public Sach(JSONObject o) throws JSONException
    {
        id = o.getString("id");
        tenSach = o.getString("tenSach");
        linkAnh = o.getString("linkAnh");
    }
    public Sach(String tenSach, String linkAnh) {
        this.tenSach = tenSach;
        this.linkAnh = linkAnh;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
