package com.flight.reser.service;

import com.flight.reser.payload.ReservationDTO;
import com.flight.reser.payload.ReservationUpdateRequestDto;

public interface ReservationService {
    public ReservationDTO createReservation(ReservationDTO reservationDTO);

    ReservationDTO getReservation(int id);
    public ReservationDTO updateReservation(int id, ReservationUpdateRequestDto reservationDTO);
    public void deleteReservation(int id);
}
