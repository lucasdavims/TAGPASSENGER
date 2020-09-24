package com.br.tcc.tagpassenger.domain.trip;

import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;

import java.util.Date;
import java.util.List;

/**
 * Created by Davi on 23/09/2020.
 */

public class Trip {

    private Long id;
    private Date begin;
    private Date end;
    private Vehicle vehicle;
    private List<Passenger> passengers;

    public Trip() {
    }

    public Trip(Long id, Date begin, Date end, Vehicle vehicle, List<Passenger> passengers) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.vehicle = vehicle;
        this.passengers = passengers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
