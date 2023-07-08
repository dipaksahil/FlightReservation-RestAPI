package com.flight.reser.Exception;

import org.springframework.http.HttpStatus;

public class FlightReservationException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;

    public FlightReservationException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}