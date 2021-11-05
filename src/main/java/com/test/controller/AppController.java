package com.test.controller;

import com.test.model.Transaction;
import com.test.model.User;
import com.test.model.enums.TransactionType;
import com.test.service.TransactionService;
import com.test.service.UserService;
import com.test.util.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/home")
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public String home() {
        System.out.println("");
        return "homepage.html";
    }

    @GetMapping("/personal-account")
    public String personalAccount() {
        System.out.println("");
        return "personal_account.html";
    }


    @GetMapping("/registerUser")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form.html";
    }

    @GetMapping("/process_register")
    public String processRegistration(User user) throws DuplicateException, DuplicateVerificationCode {
        userService.save(user);
        return "verification.html";
    }

    @GetMapping("verify-code")
    public String processVerification(String verificationCode) throws NotFoundException, OutOfTimeException {
        userService.verifyCode(verificationCode);
        return "personal_account.html";
    }

    @GetMapping("process_login")
    public String processLogin(String email, String password) throws NotFoundException {
        userService.logIn(email, password);
        return "personal_account.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login.html";
    }

    @GetMapping("/transaction")
    public String transaction(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "transaction.html";
    }

    @GetMapping("/create-transaction")
   // @RolesAllowed("ROLE_ADMIN")
    public String createTransaction(double amount, TransactionType transactionType) throws NotFoundException, InsufficientResources {
       /* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);*/
        /* String currentUserName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        // String username = principal.getName();
        System.out.println("0000000000000000000000000" + currentUserName);*/
        //  User user = userService.getByEmail(currentPrincipalName);

        transactionService.createTransaction(1, amount, transactionType);
        return "personal_account.html";
    }

    @GetMapping("/transactions")
    public String getAll(Model model) {
        List<Transaction> transactions = transactionService.getAll();
        model.addAttribute("transactions", transactions);
        return "transaction_list.html";

    }

    @GetMapping("/log-out")
    public String logOut() {
        return "homepage.html";
    }

    @GetMapping("/cancel-transaction")
    public String cancelTransaction(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "canceling_process.html";
    }

    @GetMapping("/process_canceling/{id}")
    public String cancelTransactionById(@PathVariable int id) {
        transactionService.cancelTransaction(id);
        return "personal_account.html";
    }

}
