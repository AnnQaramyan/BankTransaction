package com.test.controller;

import com.test.model.Transaction;
import com.test.model.User;
import com.test.service.TransactionService;
import com.test.util.exceptions.InsufficientResources;
import com.test.util.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;


    @PostMapping("/create-transaction")
    public void createTransaction(@Valid @RequestBody Transaction transaction, @Valid @RequestBody User user) throws NotFoundException, InsufficientResources {
        transactionService.createTransaction(transaction, user);
    }

}
