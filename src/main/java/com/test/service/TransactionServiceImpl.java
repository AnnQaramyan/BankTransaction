package com.test.service;

import com.test.model.Transaction;
import com.test.model.User;
import com.test.model.enums.TransactionStatus;
import com.test.model.enums.TransactionType;
import com.test.repository.TransactionRepository;
import com.test.repository.UserRepository;
import com.test.util.exceptions.InsufficientResources;
import com.test.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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
    public void createTransaction(int userID, double amount, TransactionType transactionType) throws NotFoundException, InsufficientResources {
        User user = userService.getById(userID);
        Transaction transaction = new Transaction();
        transaction.setTransactionStatus(TransactionStatus.PENDING);
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        if (user.getLogInStatus().toString().equals("ACTIVE")) {
            Date date = new Date();
            transaction.setDate(date);
            transaction.setUser(user);

            if (transaction.getTransactionType().toString().equals("DEPOSIT")) {
             user.setAccount(user.getAccount() + amount);
            } else if (transaction.getTransactionType().toString().equals("WITHDRAWAL")) {
                if (user.getAccount() - transaction.getAmount() < 0) {
                    throw new InsufficientResources("There in not enough resources for this transaction");
                } else {
                    user.setAccount(user.getAccount() - transaction.getAmount());
                }
            } else {
                throw new NotFoundException("There isn't this type of transaction");
            }
            user.getUser_id().add(transaction);
            userRepository.save(user);
            transactionRepository.save(transaction);
        } else {
            cancelTransaction(transaction.getId());
        }
    }

    @Override
    public void cancelTransaction(int id) {
        if(getById(id).getTransactionStatus().toString().equals("PENDING")) {
           transactionRepository.deleteById(id);
          // List<Transaction> transactions = user.getTransactions();
          // transactions.remove(id);
          // user.setTransactions(transactions);
         //  userRepository.save(user);
      }
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.
                findAll();
               // .stream().sorted().collect(Collectors.toList());
    }

    @Override
    public Transaction getById(int id) {
        return transactionRepository.getById(id);
    }

    @Override
    public void deleteById(int id) {
        transactionRepository.deleteById(id);
    }


}
