package com.test.controller;

import com.test.model.Transaction;
import com.test.model.User;
import com.test.service.UserService;
import com.test.util.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    //  @RolesAllowed(value = "ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        List<User> userList = userService.getAll();
        return ResponseEntity.ok(userList);

    }

    // @RolesAllowed(value = "ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-email")
    //  @RolesAllowed(value = "ROLE_ADMIN")
    public ResponseEntity<User> getByEmail(@RequestParam String email) {
        User user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    // @RolesAllowed(value = "ROLE_ADMIN")
    public ResponseEntity<User> getById(@PathVariable int id) throws NotFoundException {
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    //   @RolesAllowed(value = "ROLE_ADMIN")
    @PostMapping
    public void save(@Valid @RequestBody User user) throws DuplicateVerificationCode, DuplicateException {
        userService.save(user);
    }

    //working
    @PostMapping("/login")
    // @RolesAllowed(value = "ROLE_USER")
    public boolean login(@RequestParam String email, @RequestParam String password) throws NotFoundException {
        if (userService.logIn(email, password)) {
            return true;
        }
        return false;
    }

    @PostMapping("/save-admin")
    public void saveAdministrator(@Valid @RequestBody User user) throws DuplicateVerificationCode, DuplicateException {
        userService.saveAdministrator(user);
    }

    @GetMapping("/verify-code")
    public void verifyCode(@RequestParam String code) throws NotFoundException, OutOfTimeException {
        userService.verifyCode(code);
    }

}
