package com.test.controller;

import com.test.model.Transaction;
import com.test.model.enums.TransactionType;
import com.test.service.TransactionService;
import com.test.util.exceptions.InsufficientResources;
import com.test.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping("/create-transaction")
    public void createTransaction(@RequestParam int userID, @RequestParam double amount, @RequestParam TransactionType
            transactionType) throws NotFoundException, InsufficientResources {
        transactionService.createTransaction(userID, amount, transactionType);
    }

    @GetMapping("/save")
    public void save(@RequestParam double amount, @RequestParam TransactionType
            transactionType) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transactionService.save(transaction);
    }
}
