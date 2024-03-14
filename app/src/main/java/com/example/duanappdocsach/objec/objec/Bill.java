package com.example.duanappdocsach.objec.objec;

public class Bill {
    private String buyerName;
    private String buyerPhoneNumber;
    private String buyerEmail;
    private String packageName;
    private Integer price;
    private String status; // Trường trạng thái của hóa đơn

    public Bill(String buyerName, String buyerPhoneNumber, String buyerEmail, String packageName, int price, String status) {
        this.buyerName = buyerName;
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.buyerEmail = buyerEmail;
        this.packageName = packageName;
        this.price = price;
        this.status = status; // Khởi tạo trạng thái
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getPackageName() {
        return packageName;
    }

    public Integer getPrice() {
        return price;
    }
}
