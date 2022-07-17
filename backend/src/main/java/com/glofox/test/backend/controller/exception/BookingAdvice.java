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
    @ExceptionHandler(BookingDateUnavailable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BookingDateUnavailable activityNotFoundHandler(BookingDateUnavailable ex) {
        return ex;
    }
}
