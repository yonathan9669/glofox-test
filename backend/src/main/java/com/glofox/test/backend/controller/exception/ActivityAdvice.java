package com.glofox.test.backend.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ActivityAdvice {

    @ResponseBody
    @ExceptionHandler(TypeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    TypeNotFoundException typeNotFoundHandler(TypeNotFoundException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    EventNotFoundException eventNotFoundHandler(EventNotFoundException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    UserNotFoundException userNotFoundHandler(UserNotFoundException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(BadDateTimeOrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BadDateTimeOrderException badTimeOrderHandler(BadDateTimeOrderException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(ActivityNotSavedException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    ActivityNotSavedException activityNotSavedHandler(ActivityNotSavedException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(InconsistentCapacityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    InconsistentCapacityException inconsistentCapacityHandler(InconsistentCapacityException ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(IncorrectTimeFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    IncorrectTimeFormatException incorrectTimeFormatHandler(IncorrectTimeFormatException ex) {
        return ex;
    }
}
