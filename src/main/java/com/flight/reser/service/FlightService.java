package com.flight.reser.service;

import com.flight.reser.payload.FlightDTO;

import java.util.Date;
import java.util.List;

public interface FlightService {
    public FlightDTO createFlight(FlightDTO flightDTO);
    public FlightDTO getFlight(int id);
    public List<FlightDTO> getAllFlights();
    public FlightDTO updateFlight(int id, FlightDTO flightDTO);

    public void deleteFlight(int id);

    List<FlightDTO> searchFlights(String departureCity, String arrivalCity, Date dateOfDeparture);
}
