package com.upgrad.bookingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.upgrad.bookingservice.constants.PaymentModes;
import com.upgrad.bookingservice.dto.TransactionRequestDTO;
import com.upgrad.bookingservice.model.BookingInfoEntity;
import com.upgrad.bookingservice.repository.BookingRepository;
import com.upgrad.bookingservice.util.DateUtil;
import com.upgrad.bookingservice.util.RestUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

@Log4j2
@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    public ResponseEntity bookRooms(String toDate, String fromDate, String aadharNumber, int numOfRooms){
        try{
            ArrayList<String> roomNumbers=this.getRandomNumbers(numOfRooms);
            log.info("the room numbers :{} " , roomNumbers);
            if(roomNumbers!= null && roomNumbers.size() > 0){
                Date from = DateUtil.getDate(fromDate);
                Date to = DateUtil.getDate(toDate);
                Long dayDiff = DateUtil.daysDiff(from,to);
                int roomPrice = (int) (1000* numOfRooms*(dayDiff));
                BookingInfoEntity bookingInfoEntity = new BookingInfoEntity(from,to,aadharNumber,numOfRooms,roomPrice,new Date());
                log.info("Saving booking :{} " , bookingInfoEntity.toString());
                bookingInfoEntity = bookingRepository.save(bookingInfoEntity);
                return new ResponseEntity<>(bookingInfoEntity, HttpStatus.CREATED);
            }
        } catch (Exception ex){
            log.error("Exception occured due to :{} " , ex.getMessage());
        }

         return new ResponseEntity<>(new BookingInfoEntity(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity doPaymentForBooking(TransactionRequestDTO requestDTO , Integer bookingId){
        String paymentUrl = "http://localhost:8081/payment/transaction";
        try{
            BookingInfoEntity bookingInfoEntity = bookingRepository.findByBookingId(bookingId);
            if(bookingInfoEntity == null){
                return new ResponseEntity<>("Invalid Boooking id", HttpStatus.BAD_REQUEST);
            }
            if(PaymentModes.getPaymentModeList().contains(requestDTO.getPaymentMode()) == false){
                return new ResponseEntity<>("Invalid mode of payment", HttpStatus.BAD_REQUEST);
            }
            ResponseEntity<Integer> res = RestUtil.callPOSTRequestWithJson(paymentUrl,new Gson().toJson(requestDTO),new HashMap<>());
            Integer txnId = res.getBody();
            log.info("payment result :{} " ,txnId);
            bookingInfoEntity.setTransactionId(txnId);
            bookingRepository.save(bookingInfoEntity);
            return new ResponseEntity<>(bookingInfoEntity, HttpStatus.CREATED);
        } catch (JsonProcessingException e){
            log.error("json exception occured due to : {} " , e.getMessage());
        } catch (Exception ex){
            log.error("Exception occured due to : {} " , ex.getMessage());
        }

        return new ResponseEntity<>(new BookingInfoEntity(), HttpStatus.BAD_REQUEST);
    }

    public static ArrayList<String> getRandomNumbers(int count){
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String>numberList = new ArrayList<String>();
        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }
        return numberList;
    }
}
