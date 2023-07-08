package com.flight.reser.entity;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "RESERVATION")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "CHECKED_IN")
    private boolean checkedIn;

    @Column(name = "NUMBER_OF_BAGS")
    private int numberOfBags;

    @Column(name = "CREATED")
    private Timestamp created;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PASSENGER_ID", referencedColumnName = "ID")
    private Passenger passenger;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FLIGHT_ID", referencedColumnName = "ID")
    private Flight flight;
}