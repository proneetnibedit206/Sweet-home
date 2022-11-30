package com.upgrad.bookingservice.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="booking")
@ToString
public class BookingInfoEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "bookingId")
    public Integer bookingId;

    @Column(name = "fromDate")
    public Date fromDate;

    @Column(name = "toDate")
    public Date toDate;

    @Column(name = "aadharNumber")
    public String aadharNumber;

    @Column(name = "numOfRooms")
    public Integer numOfRooms;

    @Column(name = "roomPrice")
    @NotNull
    public Integer roomPrice;

    @Column(name = "transactionId" , columnDefinition = "integer default 0")
    public Integer transactionId = 0;

    @Column(name = "bookedOn")
    Date bookedOn;

    public BookingInfoEntity(Date fromDate, Date toDate, String aadharNumber, Integer numOfRooms, Integer roomPrice, Date bookedOn) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.aadharNumber = aadharNumber;
        this.numOfRooms = numOfRooms;
        this.roomPrice = roomPrice;
        this.bookedOn = bookedOn;
    }
}
