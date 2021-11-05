package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.model.enums.TransactionStatus;
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

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name = "date")
    private Date date;

    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column
    private Double amount;

    public Transaction(int id, @NotNull TransactionType transactionType, Date date, User user, Double amount) {
        this.id = id;
        this.transactionType = transactionType;
        this.date = date;
        this.user = user;
        this.amount = amount;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public int compareTo(Transaction o) {
        return getDate().compareTo(o.getDate());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType=" + transactionType +
                ", date=" + date +
                ", user=" + user +
                ", amount=" + amount +
                '}';
    }
}
