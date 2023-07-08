package com.flight.reser.controller;

import com.flight.reser.payload.PassengerDTO;
import com.flight.reser.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;
    // http://localhost:8080/api/passengers
    @PostMapping
    public PassengerDTO createPassenger(@RequestBody PassengerDTO passengerDTO) {
        return passengerService.createPassenger(passengerDTO);
    }
    // http://localhost:8080/api/passengers/{id}
    @GetMapping("/{id}")
    public PassengerDTO getPassenger(@PathVariable int id) {
        return passengerService.getPassenger(id);
    }
    // http://localhost:8080/api/passengers
    @GetMapping
    public List<PassengerDTO> getAllPassengers() {
        return passengerService.getAllPassengers();
    }
    // http://localhost:8080/api/passengers/{id}
    @PutMapping("/{id}")
    public PassengerDTO updatePassenger(@PathVariable int id, @RequestBody PassengerDTO passengerDTO) {
        return passengerService.updatePassenger(id, passengerDTO);
    }
    // http://localhost:8080/api/passengers/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePassenger(@PathVariable int id) {
        passengerService.deletePassenger(id);
        return new ResponseEntity<>("Passenger deleted successfully", HttpStatus.OK);
    }
}
