package com.test.controller;

import com.test.model.User;
import com.test.service.UserService;
import com.test.util.exceptions.DuplicateException;
import com.test.util.exceptions.DuplicateVerificationCode;
import com.test.util.exceptions.NotFoundException;
import com.test.util.exceptions.OutOfTimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    //  @RolesAllowed(value = "ROLE_ADMIN")
    @GetMapping("/user")
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
    @GetMapping("/saving")
    public String save(@Valid @RequestBody User user) throws DuplicateVerificationCode, DuplicateException {
        System.out.println("");
        userService.save(user);
        return "login.html";
    }

    //working
    @GetMapping("/login")
    // @RolesAllowed(value = "ROLE_USER")
    public String login(@RequestParam String email, @RequestParam String password) throws NotFoundException {
        System.out.println("");
        if (userService.logIn(email, password)) {
            return "hpmepage.html";
        }
        return "login.html";
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
