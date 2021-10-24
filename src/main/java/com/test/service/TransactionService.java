package com.test.service;

import com.test.model.Transaction;
import com.test.model.User;
import com.test.util.exceptions.InsufficientResources;
import com.test.util.exceptions.NotFoundException;

import java.util.List;

public interface TransactionService {

    void createTransaction(Transaction transaction, User user) throws NotFoundException, InsufficientResources;

    List<Transaction> getAll();
}
