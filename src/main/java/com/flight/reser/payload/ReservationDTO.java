package com.flight.reser.payload;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ReservationDTO {

    private int id;
    private boolean checkedIn;
    private int numberOfBags;
    private Timestamp created;
    private PassengerDTO passenger;
    private FlightDTO flight;

}

