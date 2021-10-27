package com.test.service;

import com.test.model.Authority;
import com.test.model.User;
import com.test.model.enums.Status;
import com.test.repository.AuthorityRepository;
import com.test.repository.UserRepository;
import com.test.util.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RandomCodeGeneration randomCodeGeneration;

    @Autowired
    private EmailServerService emailServerService;

    @Autowired
    private AuthorityRepository authorityRepository;


    @Autowired
    private DateOfVerificationCode dateOfVerificationCode;


    @Override
    public User getById(int id) throws NotFoundException {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new NotFoundException("Couldn't found book with current id: " + id);
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }


    @Override
    public User getByVerificationCode(String code) {
        return userRepository.getByVerificationCode(code);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void save(User user) throws DuplicateException, DuplicateVerificationCode {
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        if (userRepository.getByEmail(user.getEmail()) != null) {
            throw new DuplicateException("Duplicate email");
        }

        String vfCode = randomCodeGeneration.generateRandomCode();
        if (userRepository.getByVerificationCode(vfCode) != null) {
            throw new DuplicateVerificationCode("There is duplicate verification code");
        } else {
            user.setVerificationCode(vfCode);
            user.setStatus(Status.UNVERIFIED);

            emailServerService.sendSimpleMessage(user.getEmail(), "Registration", vfCode);
            Date date = new Date();
            user.setVerificationCodeDate(date);
            Set<Authority> authorities = new HashSet<>();
            authorities.add(authorityRepository.getByName("ROLE_USER"));
            user.setAuthorities(authorities);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void saveAdministrator(User user) throws DuplicateException, DuplicateVerificationCode {
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        if (userRepository.getByEmail(user.getEmail()) != null) {
            throw new DuplicateException("Duplicate email");
        }

        String vfCode = randomCodeGeneration.generateRandomCode();
        if (userRepository.getByVerificationCode(vfCode) != null) {
            throw new DuplicateVerificationCode("There is duplicate verification code");
        } else {
            user.setVerificationCode(vfCode);
            user.setStatus(Status.UNVERIFIED);

            emailServerService.sendSimpleMessage(user.getEmail(), "Registration", vfCode);
            Date date = new Date();
            user.setVerificationCodeDate(date);
            Set<Authority> authorities = new HashSet<>();
            authorities.add(authorityRepository.getByName("ROLE_ADMIN"));
            user.setAuthorities(authorities);
            userRepository.save(user);
        }
    }

    @Override
    public boolean logIn(String email, String password) throws NotFoundException {
        if(getByEmail(email) == null){
            throw new  NotFoundException("There isn't user with this email");
        }else {
            User user = new User();
            user = getByEmail(email);
            boolean isPasswordMatches = passwordEncoder.matches(
                    password,
                    user.getPassword()
            );
            if(isPasswordMatches && user.getStatus().toString().equals("ACTIVE")){
               return true;
            }
        }
        throw new NotFoundException("Username Or Password is incorrect");
    }

    @Transactional
    @Override
    public void verifyCode(String code) throws NotFoundException, OutOfTimeException {
        User user = userRepository.getByVerificationCode(code);

        if (user == null) {
            throw new NotFoundException("Wrong verification code");
        } else {

            if (!dateOfVerificationCode.dateOfVfCode(user)) {
                throw new OutOfTimeException("verification code is out of Time");
            }

            user.setStatus(Status.ACTIVE);
            user.setVerificationCode(null);
            userRepository.save(user);
        }
    }

}
