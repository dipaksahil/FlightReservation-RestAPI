package com.flight.reser.repository;

import com.flight.reser.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
   // List<Flight> findByDepartureCityAndArrivalCityAndDateOfDeparture(String departureCity, String arrivalCity, Date dateOfDeparture);

    @Query(value = "SELECT * FROM FLIGHT f WHERE f.DEPARTURE_CITY = :departureCity AND f.ARRIVAL_CITY = :arrivalCity AND DATE(f.DATE_OF_DEPARTURE) = :dateOfDeparture", nativeQuery = true)
    List<Flight> findByDepartureCityAndArrivalCityAndDateOfDeparture(@Param("departureCity") String departureCity, @Param("arrivalCity") String arrivalCity, @Param("dateOfDeparture") @Temporal(TemporalType.DATE) Date dateOfDeparture);

}

