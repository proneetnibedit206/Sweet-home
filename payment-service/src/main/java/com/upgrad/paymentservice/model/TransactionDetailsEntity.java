package com.upgrad.paymentservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="transaction")
@ToString
public class TransactionDetailsEntity implements Serializable {

    @Id
    @GeneratedValue
    public Integer transactionId;

    @Column(name = "paymentMode")
    public String  paymentMode;

    @Column(name = "bookingId")
    public Integer bookingId;

    @Column(name = "upiId")
    public String upiId;

    @Column(name = "cardNumber")
    public String cardNumber;


    public TransactionDetailsEntity(String paymentMode, Integer bookingId, String upiId, String cardNumber) {
        this.paymentMode = paymentMode;
        this.bookingId = bookingId;
        this.upiId = upiId;
        this.cardNumber = cardNumber;
    }
}

