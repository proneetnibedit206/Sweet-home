package com.upgrad.bookingservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequestDTO {
    String toDate;
    String fromDate;
    String aadharNumber;
    int numOfRooms;
}
