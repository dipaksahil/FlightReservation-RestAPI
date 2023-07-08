package com.flight.reser.service.impl;

import com.flight.reser.entity.Passenger;
import com.flight.reser.payload.PassengerDTO;
import com.flight.reser.repository.PassengerRepository;
import com.flight.reser.service.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PassengerDTO createPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = modelMapper.map(passengerDTO, Passenger.class);
        Passenger savedPassenger = passengerRepository.save(passenger);
        return modelMapper.map(savedPassenger, PassengerDTO.class);
    }

    public PassengerDTO getPassenger(int id) {
        return passengerRepository.findById(id).map(passenger -> modelMapper.map(passenger, PassengerDTO.class))
                .orElseThrow(() -> new RuntimeException("Passenger with id " + id + " not found"));
    }
//    public PassengerDTO getPassenger(int id) {
//        Optional<Passenger> optionalPassenger = passengerRepository.findById(id);
//        if (optionalPassenger.isPresent()) {
//            Passenger passenger = optionalPassenger.get();
//            PassengerDTO passengerDTO = modelMapper.map(passenger, PassengerDTO.class);
//            return passengerDTO;
//        } else {
//            throw new RuntimeException("Passenger with id " + id + " not found");
//        }
//    }
    public List<PassengerDTO> getAllPassengers() {
//        return passengerRepository.findAll().stream()
//                .map(passenger -> modelMapper.map(passenger, PassengerDTO.class))
//                .collect(Collectors.toList());
        List<Passenger> passengers = passengerRepository.findAll();
        List<PassengerDTO> dto = passengers.stream().map(passenger -> modelMapper.map(passenger, PassengerDTO.class)).collect(Collectors.toList());
        return dto;
    }

    public PassengerDTO updatePassenger(int id, PassengerDTO passengerDTO) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger with id " + id + " not found"));

        passenger.setFirstName(passengerDTO.getFirstName());
        passenger.setLastName(passengerDTO.getLastName());
        passenger.setMiddleName(passengerDTO.getMiddleName());
        passenger.setEmail(passengerDTO.getEmail());
        passenger.setPhone(passengerDTO.getPhone());

        Passenger updatedPassenger = passengerRepository.save(passenger);
        return modelMapper.map(updatedPassenger, PassengerDTO.class);
    }

    public void deletePassenger(int id) {
        passengerRepository.deleteById(id);
    }
}