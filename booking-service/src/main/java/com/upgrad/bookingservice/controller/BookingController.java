package com.upgrad.bookingservice.controller;

import com.upgrad.bookingservice.dto.BookingRequestDTO;
import com.upgrad.bookingservice.dto.TransactionRequestDTO;
import com.upgrad.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @PostMapping("/booking")
    public @ResponseBody
    ResponseEntity createCustomer(@RequestBody BookingRequestDTO requestDTO){
        return bookingService.bookRooms(requestDTO.getToDate(),requestDTO.getFromDate(),requestDTO.getAadharNumber(),requestDTO.getNumOfRooms());
    }

    @PostMapping("/booking/{bookingId}/transaction")
    public @ResponseBody
    ResponseEntity doPaymentForBooking(@RequestBody TransactionRequestDTO requestDTO,
                                       @PathVariable("bookingId") Integer bookingId){
        return bookingService.doPaymentForBooking(requestDTO , bookingId);
    }
}
