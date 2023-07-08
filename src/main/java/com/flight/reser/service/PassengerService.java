package com.flight.reser.service;

import com.flight.reser.payload.PassengerDTO;
import java.util.List;

public interface PassengerService {

    public PassengerDTO createPassenger(PassengerDTO passengerDTO);
    public PassengerDTO getPassenger(int id);
    public List<PassengerDTO> getAllPassengers();
    public PassengerDTO updatePassenger(int id, PassengerDTO passengerDTO);
    public void deletePassenger(int id);
}
