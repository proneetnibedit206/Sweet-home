package com.upgrad.bookingservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionRequestDTO {

    Integer bookingId;
    String paymentMode;
    String upiId;
    String cardNumber;
}
