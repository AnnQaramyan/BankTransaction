package com.test.model;

import com.test.model.enums.TransactionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Transaction implements  Comparable<Transaction>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType transactionType;

    @Column(name = "date")
    private Date date;

    @JoinColumn(name = "transaction_id")
    @ManyToOne
    private User user;

    @Column
    private int userId;

    @Column
    private double amount;

    public Transaction(int id, @NotNull TransactionType transactionType, Date date, @NotNull double requiredAmount) {
        this.id = id;
        this.transactionType = transactionType;
        this.date = date;
        this.amount = requiredAmount;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(Transaction o) {
        return getDate().compareTo(o.getDate());
    }
}
