package com.flight.reser.payload;

import lombok.Data;

@Data
public class ReservationUpdateRequestDto {
    private int id;
    private boolean checkedIn;
    private int numberOfBags;

}