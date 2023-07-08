package com.flight.reser.payload;

import lombok.Data;

@Data
public class PassengerDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;

}
