package com.glofox.test.backend.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BookingAdvice {
    @ResponseBody
    @ExceptionHandler(ExclusiveFieldsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ExclusiveFieldsException exclusiveFieldsHandler(ExclusiveFieldsException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(BookingNotSavedException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    BookingNotSavedException bookingNotSavedHandler(BookingNotSavedException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(ActivityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ActivityNotFoundException activityNotFoundHandler(ActivityNotFoundException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(BookingDateUnavailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BookingDateUnavailableException bookingDateUnavailableHandler(BookingDateUnavailableException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(DuplicatedBookingException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    DuplicatedBookingException duplicatedBookingHandler(DuplicatedBookingException ex) {
        return ex;
    }
}
