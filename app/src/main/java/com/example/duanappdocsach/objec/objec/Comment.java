package com.example.duanappdocsach.objec.objec;

public class Comment {
    private int id; // Thêm thuộc tính ID
    private String content;
    private String author;
    public Comment() {
        // Không cần thực hiện thêm bất kỳ thao tác nào
    }

    public Comment(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public int getId() { // Thêm phương thức getter cho ID
        return id;
    }

    public void setId(int id) { // Thêm phương thức setter cho ID
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
    @Override
    public String toString() {
        return content; // Trả về nội dung của ghi chú khi được chuyển thành chuỗi
    }
}
