package com.test.service;

import com.test.model.Transaction;
import com.test.model.User;
import com.test.model.enums.TransactionType;
import com.test.util.exceptions.InsufficientResources;
import com.test.util.exceptions.NotFoundException;

import java.util.List;

public interface TransactionService {

    void createTransaction(int userId, double amount, TransactionType transactionType) throws NotFoundException, InsufficientResources;

    List<Transaction> getAll();
    Transaction getById(int id);
    void deleteById(int id);
    void cancelTransaction(int id);
    void save(Transaction transaction);
}
