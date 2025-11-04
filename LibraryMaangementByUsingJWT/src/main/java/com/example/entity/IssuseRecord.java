package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name="IssuseRecord")
public class IssuseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  IssuseId;

    private String issuseDate;
    private String dueDate;
    private  String returndate;
    private boolean isReturn;
    public IssuseRecord(){

    }



    public IssuseRecord(String issuseDate, String dueDate, String returndate, boolean isReturn) {
        this.issuseDate = issuseDate;
        this.dueDate = dueDate;
        this.returndate = returndate;
        this.isReturn = isReturn;
    }

    public String getIssuseDate() {
        return issuseDate;
    }

    public void setIssuseDate(String issuseDate) {
        this.issuseDate = issuseDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name="book_id")
    private Book book;

    public void setBook(Book book) {
    }

    public void setUser(User user) {
    }
}
