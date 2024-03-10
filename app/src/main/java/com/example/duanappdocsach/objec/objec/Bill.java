package com.example.duanappdocsach.objec.objec;

public class Bill {
    private String buyerName;
    private String buyerPhoneNumber;
    private String buyerEmail;
    private String packageName;
    private Integer price;

    public Bill(String buyerName, String buyerPhoneNumber, String buyerEmail, String packageName, int price) {
        this.buyerName = buyerName;
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.buyerEmail = buyerEmail;
        this.packageName = packageName;
        this.price = price;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getPackageName() {
        return packageName;
    }

    public Integer getPrice() {
        return price;
    }
}
