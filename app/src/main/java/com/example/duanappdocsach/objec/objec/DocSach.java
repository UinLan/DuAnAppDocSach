package com.example.duanappdocsach.objec.objec;

public class DocSach {
    private String tenSach,linkAnh;
    public DocSach()
    {}
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
