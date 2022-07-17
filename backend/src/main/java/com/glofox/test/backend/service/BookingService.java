package com.glofox.test.backend.service;

import com.glofox.test.backend.controller.exception.ActivityNotFoundException;
import com.glofox.test.backend.controller.exception.BookingDateUnavailable;
import com.glofox.test.backend.controller.exception.BookingNotSavedException;
import com.glofox.test.backend.controller.exception.UserNotFoundException;
import com.glofox.test.backend.dto.BookingDto;
import com.glofox.test.backend.dto.BookingMapper;
import com.glofox.test.backend.dto.BookingsDto;
import com.glofox.test.backend.entity.Activity;
import com.glofox.test.backend.entity.Booking;
import com.glofox.test.backend.repository.ActivityRepository;
import com.glofox.test.backend.repository.BookingRepository;
import com.glofox.test.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookings;
    private final ActivityRepository activities;
    private final UserRepository users;

    public BookingService(BookingRepository bookings, ActivityRepository activities, UserRepository users) {
        this.bookings = bookings;
        this.activities = activities;
        this.users = users;
    }

    public List<Booking> save(BookingsDto bookingsDto) {
        // User | Validation
        Integer userId = bookingsDto.getMember();
        users.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        // Activity | Validation
        Integer activityId = bookingsDto.getActivity();
        Activity activity = activities.findById(activityId).orElseThrow(() -> new ActivityNotFoundException(activityId));

        List<BookingDto> newBookingsDto = bookingsDto.toBookingList();
        List<Booking> newBookings = newBookingsDto.stream().map(bookingDto -> {
            // Availability check | Validation
            LocalDate date = bookingDto.getDate();
            if (!activity.canBook(date)) throw new BookingDateUnavailable(activityId, date);

            return BookingMapper.INSTANCE.incoming(bookingDto);
        }).toList();

        try {
            bookings.saveAll(newBookings);
        } catch (RuntimeException e) {
            throw new BookingNotSavedException(e.getMessage());
        }

        return newBookings;
    }
}
