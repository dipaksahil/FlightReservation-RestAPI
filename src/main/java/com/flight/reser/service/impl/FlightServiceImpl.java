package com.flight.reser.service.impl;

import com.flight.reser.entity.Flight;
import com.flight.reser.payload.FlightDTO;
import com.flight.reser.repository.FlightRepository;
import com.flight.reser.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        Flight flight = modelMapper.map(flightDTO, Flight.class);
        Flight savedFlight = flightRepository.save(flight);
        return modelMapper.map(savedFlight, FlightDTO.class);
    }
    @Override
    public FlightDTO getFlight(int id) {
        return flightRepository.findById(id).map(flight -> modelMapper.map(flight, FlightDTO.class))
                .orElseThrow(() -> new RuntimeException("Flight with id " + id + " not found"));
    }
    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public FlightDTO updateFlight(int id, FlightDTO flightDTO) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Flight not found"));

        if(flightDTO.getFlightNumber() != null) {
            flight.setFlightNumber(flightDTO.getFlightNumber());
        }
        if(flightDTO.getOperatingAirlines() != null) {
            flight.setOperatingAirlines(flightDTO.getOperatingAirlines());
        }
        if(flightDTO.getDepartureCity() != null) {
            flight.setDepartureCity(flightDTO.getDepartureCity());
        }
        if(flightDTO.getArrivalCity() != null) {
            flight.setArrivalCity(flightDTO.getArrivalCity());
        }
        if(flightDTO.getDateOfDeparture() != null) {
            flight.setDateOfDeparture(flightDTO.getDateOfDeparture());
        }
        if(flightDTO.getEstimatedDepartureTime() != null) {
            flight.setEstimatedDepartureTime(flightDTO.getEstimatedDepartureTime());
        }
        Flight updatedFlight = flightRepository.save(flight);
        return modelMapper.map(updatedFlight, FlightDTO.class);
    }
    @Override
    public void deleteFlight(int id) {
        flightRepository.deleteById(id);
    }
    @Override
    public List<FlightDTO> searchFlights(String departureCity, String arrivalCity, Date dateOfDeparture) {
        List<Flight> flights = flightRepository.findByDepartureCityAndArrivalCityAndDateOfDeparture(departureCity, arrivalCity, dateOfDeparture);
        return flights.stream().map(flight -> modelMapper.map(flight, FlightDTO.class)).collect(Collectors.toList());
    }
}