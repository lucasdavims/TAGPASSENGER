package com.br.tcc.tagpassenger.features.controltrip;

import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;

import java.util.List;

/**
 * Created by Davi on 31/10/2020.
 */

public class CurrentTripDto {

    private Trip trip;
    private List<Passenger> passengers;

    public CurrentTripDto() {
    }

    public CurrentTripDto(Trip trip, Vehicle vehicle, List<Passenger> passengers) {
        this.trip = trip;
        this.passengers = passengers;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
