package com.flight.reser.controller;

import com.flight.reser.payload.FlightDTO;
import com.flight.reser.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/api/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
// http://localhost:8080/api/flight/create
    @PostMapping("/create")
    public FlightDTO createFlight(@RequestBody FlightDTO flightDTO) {
        return flightService.createFlight(flightDTO);
    }

    // http://localhost:8080/api/flight/{id}
    @GetMapping("/{id}")
    public FlightDTO getFlight(@PathVariable int id) {
        return flightService.getFlight(id);
    }

    @GetMapping("/all")
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PutMapping("/update/{id}")
    public FlightDTO updateFlight(@PathVariable int id, @RequestBody FlightDTO flightDTO) {
        return flightService.updateFlight(id, flightDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable int id) {
        flightService.deleteFlight(id);
        return new ResponseEntity<>("Flight deleted successfully", HttpStatus.OK);
    }
    // http://localhost:8080/api/flight/search
    @GetMapping("/search")
    public List<FlightDTO> searchFlights(@RequestParam String departureCity, @RequestParam String arrivalCity, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateOfDeparture) {
        //System.out.println(departureCity);
        //System.out.println(arrivalCity);
        return flightService.searchFlights(departureCity, arrivalCity, dateOfDeparture);
    }
}