package com.glofox.test.backend.controller;

import com.glofox.test.backend.dto.BookingsDto;
import com.glofox.test.backend.entity.Booking;
import com.glofox.test.backend.service.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping("/bookings")
    BookingsDto handler(@RequestBody BookingsDto bookingsDto) {
        List<Booking> bookings = service.save(bookingsDto);
        bookingsDto.setBookings(bookings);
        return bookingsDto;
    }
}
