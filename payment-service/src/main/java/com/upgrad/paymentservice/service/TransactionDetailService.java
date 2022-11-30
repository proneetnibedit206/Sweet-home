package com.upgrad.paymentservice.service;

import com.upgrad.paymentservice.model.TransactionDetailsEntity;
import com.upgrad.paymentservice.repository.TransactionDetailRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class TransactionDetailService {

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    public ResponseEntity<Integer> doPayment(Integer bookingId,String paymentMode,String upiId,String cardNumber){
        try {
            TransactionDetailsEntity transactionDetailsEntity = new TransactionDetailsEntity(paymentMode, bookingId, upiId, cardNumber);
            transactionDetailRepository.save(transactionDetailsEntity);
            return new ResponseEntity(transactionDetailsEntity.getTransactionId(), HttpStatus.CREATED);
        }catch (Exception ex){
            log.error("Exception while doing payment due to : {}" , ex.getMessage());
        }

        return new ResponseEntity(-1, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity getPaymentDetails(Integer txnId){
        TransactionDetailsEntity transactionDetailsEntity = transactionDetailRepository.findByTransactionId(txnId);
        if(transactionDetailsEntity != null){
            return new ResponseEntity(transactionDetailsEntity, HttpStatus.OK);
        }
        return new ResponseEntity(transactionDetailsEntity, HttpStatus.NO_CONTENT);
    }
}
