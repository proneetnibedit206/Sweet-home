package com.upgrad.paymentservice.repository;

import com.upgrad.paymentservice.model.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetailsEntity , Integer> {

    TransactionDetailsEntity findByTransactionId(Integer transactionId);
}
