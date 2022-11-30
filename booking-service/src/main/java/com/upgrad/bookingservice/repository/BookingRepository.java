package com.upgrad.bookingservice.repository;

import com.upgrad.bookingservice.model.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingInfoEntity , Integer> {
    BookingInfoEntity findByBookingId(Integer bookingId);
}
