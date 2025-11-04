package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String  author;
    private String isbm;
    private Integer quantity;
    private Boolean isAvailabe;

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

    private Integer bookPrice;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    private String bookName;

    public Book(){

    }

    public Book(String title, String author, String isbm, Integer quantity, Boolean isAvailabe) {
        this.title = title;
        this.author = author;
        this.isbm = isbm;
        this.quantity = quantity;
        this.isAvailabe = isAvailabe;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbm() {
        return isbm;
    }

    public void setIsbm(String isbm) {
        this.isbm = isbm;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getAvailabe() {
        return isAvailabe;
    }

    public void setAvailabe(Boolean availabe) {
        isAvailabe = availabe;
    }
}
