package com.test.service;

import com.test.model.Transaction;
import com.test.model.User;
import com.test.repository.TransactionRepository;
import com.test.repository.UserRepository;
import com.test.util.exceptions.InsufficientResources;
import com.test.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public void  createTransaction(Transaction transaction, User user) throws NotFoundException, InsufficientResources {
        if(userService.logIn(user.getEmail(), user.getPassword())) {
            Date date = new Date();
            transaction.setDate(date);
            transaction.setUser(user);

            if (transaction.getTransactionType().equals("DEPOSIT")) {
                if (transaction.getAmount() < 0) {
                    throw new InsufficientResources("There in not enough resources for this transaction");
                } else {
                    user.setAccount(user.getAccount() + transaction.getAmount());
                    transaction.setAmount(0);
                }
            } else if (transaction.getTransactionType().equals("WITHDRAWAL")) {
                if (user.getAccount() - transaction.getAmount() < 0) {
                    throw new InsufficientResources("There in not enough resources for this transaction");
                } else {
                    user.setAccount(user.getAccount() - transaction.getAmount());
                    transaction.setAmount(user.getAccount() - transaction.getAmount());
                }
            }else{
                throw new NotFoundException("there isn't this type of transaction");
            }
            user.getTransactions().add(transaction);
            userRepository.save(user);
            transactionRepository.save(transaction);
        }
    }

    @Override
    public List<Transaction> getAll() {
      return  transactionRepository.findAll().stream().sorted().collect(Collectors.toList());
    }
}
