package com.example.duanappdocsach.objec.objec;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.jar.JarException;

public class DocSach implements Serializable {
    private String tenSach,linkAnh;
    /*
    {
    "tenSach":"",
    "linkAnh":""
    }
     */
    public DocSach()
    {}
    public DocSach(JSONObject o) throws JSONException
    {
        tenSach = o.getString("tenSach");
        linkAnh = o.getString("linkAnh");


    }
    public DocSach(String tenSach, String linkAnh) {
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
}
