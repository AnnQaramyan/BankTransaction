package com.test.repository;


import com.test.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
    Transaction getById(int id);
    void deleteById(int id);
}
