package com.test.service;


import com.test.model.Transaction;
import com.test.model.User;
import com.test.util.exceptions.*;

import java.util.List;

public interface UserService {

    User getById(int id) throws   NotFoundException;

    User getByEmail(String email);

    List<User> getAll();

    void deleteById(int id);

    void save(User user) throws DuplicateException, DuplicateVerificationCode;

    void saveAdministrator(User user) throws DuplicateException, DuplicateVerificationCode;

    User getByVerificationCode(String code);

    boolean logIn(String email, String password) throws NotFoundException;

    void verifyCode(String code) throws NotFoundException, OutOfTimeException;
}
