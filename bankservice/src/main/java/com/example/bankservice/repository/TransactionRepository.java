package com.example.bankservice.repository;

import com.example.bankservice.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findBySenderAndType(String sender, String type);

    List<TransactionEntity> findBySender(String sender);

    List<TransactionEntity> findByAmount(Integer amount);

    List<TransactionEntity> findByTimestamp(Date date);

    List<TransactionEntity> findBySenderAndAmount(String sender, Integer amount);

    List<TransactionEntity> findBySenderAndTimestamp(String sender, Date date);


}
