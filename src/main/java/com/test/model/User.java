package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.model.enums.LogInStatus;
import com.test.model.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    @NotNull
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull
    private String password;

    @Column(name = "verification_code", unique = true)
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private LogInStatus logInStatus;

    @Column(name = "verification_code_date")
    private Date verificationCodeDate;


    @OneToMany(mappedBy = "user")
    private List<Transaction> user_id;

    @Column
    private double account;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;

    User(int id, @NotNull String name, @NotNull String email, @NotNull String password, String verificationCode,
         Status status, Date verificationCodeDate, Set<Authority> authorities) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
        this.status = status;
        this.verificationCodeDate = verificationCodeDate;
        this.authorities = authorities;
    }

    public User() {
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getVerificationCodeDate() {
        return verificationCodeDate;
    }

    public void setVerificationCodeDate(Date date) {
        this.verificationCodeDate = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Transaction> getUser_id() {
        return user_id;
    }

    public void setUser_id(List<Transaction> user_id) {
        this.user_id = user_id;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public LogInStatus getLogInStatus() {
        return logInStatus;
    }

    public void setLogInStatus(LogInStatus logInStatus) {
        this.logInStatus = logInStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(verificationCode, user.verificationCode) && status == user.status && Objects.equals(verificationCodeDate, user.verificationCodeDate) && Objects.equals(authorities, user.authorities);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, verificationCode, status, verificationCodeDate, authorities);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", status=" + status +
                ", verificationCodeDate=" + verificationCodeDate +
                ", authorities=" + authorities +
                '}';
    }
}
