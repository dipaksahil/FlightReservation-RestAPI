package com.flight.reser.payload;
import lombok.Data;

import java.util.Date;

@Data
public class FlightDTO {

    private int id;
    private String flightNumber;
    private String operatingAirlines;
    private String departureCity;
    private String arrivalCity;
    private Date dateOfDeparture;
    private Date estimatedDepartureTime;

}

