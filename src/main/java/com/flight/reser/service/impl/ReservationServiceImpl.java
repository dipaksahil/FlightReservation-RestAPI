package com.flight.reser.service.impl;

import com.flight.reser.entity.Flight;
import com.flight.reser.entity.Passenger;
import com.flight.reser.entity.Reservation;
import com.flight.reser.payload.ReservationDTO;
import com.flight.reser.payload.ReservationUpdateRequestDto;
import com.flight.reser.repository.FlightRepository;
import com.flight.reser.repository.PassengerRepository;
import com.flight.reser.repository.ReservationRepository;
import com.flight.reser.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
    public class ReservationServiceImpl implements ReservationService {
        private final ReservationRepository reservationRepository;
        private final FlightRepository flightRepository;
        private final PassengerRepository passengerRepository;
        private final ModelMapper modelMapper;

        @Autowired
        public ReservationServiceImpl(ReservationRepository reservationRepository, FlightRepository flightRepository, PassengerRepository passengerRepository, ModelMapper modelMapper) {
            this.reservationRepository = reservationRepository;
            this.flightRepository = flightRepository;
            this.passengerRepository = passengerRepository;
            this.modelMapper = modelMapper;
        }

        @Override
        public ReservationDTO createReservation(ReservationDTO reservationDTO) {
            Flight flight = flightRepository.findById(reservationDTO.getFlight().getId())
                    .orElseThrow(() -> new RuntimeException("Flight not found"));
            Passenger passenger = passengerRepository.findById(reservationDTO.getPassenger().getId())
                    .orElseThrow(() -> new RuntimeException("Passenger not found"));

            Reservation reservation = new Reservation();
            reservation.setCheckedIn(reservationDTO.isCheckedIn());
            reservation.setNumberOfBags(reservationDTO.getNumberOfBags());
            reservation.setCreated(reservationDTO.getCreated());
            reservation.setFlight(flight);
            reservation.setPassenger(passenger);

            //return reservationRepository.save(reservation);
            Reservation createdReservation = reservationRepository.save(reservation);
            return modelMapper.map(createdReservation, ReservationDTO.class);
        }

        @Override
        public ReservationDTO getReservation(int id) {
            Optional<Reservation> optionalReservation = reservationRepository.findById(id);
            if (optionalReservation.isPresent()) {
                Reservation reservation = optionalReservation.get();
                return modelMapper.map(reservation, ReservationDTO.class);
            } else {
                throw new RuntimeException("Reservation with id " + id + " not found");
            }
        }

        @Override
        public ReservationDTO updateReservation(int id, ReservationUpdateRequestDto reservationDTO) {
            Reservation reservation = reservationRepository.findById(id)
                    .orElse(null);
            if (reservation != null) {
                reservation.setCheckedIn(reservationDTO.isCheckedIn());
                reservation.setNumberOfBags(reservationDTO.getNumberOfBags());

                Reservation updatedReservation = reservationRepository.save(reservation);
                return modelMapper.map(updatedReservation, ReservationDTO.class);
            }
            return null;
        }

        @Override
        public void deleteReservation(int id) {
            reservationRepository.deleteById(id);
        }
    }
