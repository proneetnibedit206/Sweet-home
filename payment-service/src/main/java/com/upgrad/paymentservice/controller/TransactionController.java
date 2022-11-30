package com.upgrad.paymentservice.controller;

import com.upgrad.paymentservice.dto.TransactionRequestDTO;
import com.upgrad.paymentservice.service.TransactionDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransactionController {

    @Autowired
    TransactionDetailService transactionDetailService;

    @PostMapping("/transaction")
    public @ResponseBody
    ResponseEntity doPayment(@RequestBody TransactionRequestDTO requestDTO){
        return transactionDetailService.doPayment(requestDTO.getBookingId(),requestDTO.getPaymentMode(),requestDTO.getUpiId(),requestDTO.getCardNumber());
    }

    @GetMapping("/transaction/{transactionId}")
    public @ResponseBody
    ResponseEntity getPaymentDetails(@PathVariable("transactionId") Integer transactionId){
        return transactionDetailService.getPaymentDetails(transactionId);
    }


}
