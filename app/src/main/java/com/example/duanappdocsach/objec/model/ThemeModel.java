package com.example.duanappdocsach.objec.model;

public class ThemeModel {
    private int Image;
    private String Title;
    private  String Key_id;

    public ThemeModel(){

    }

    public ThemeModel(int image, String title, String key_id) {
        Image = image;
        Title = title;
        Key_id = key_id;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getKey_id() {
        return Key_id;
    }

    public void setKey_id(String key_id) {
        Key_id = key_id;
    }
}