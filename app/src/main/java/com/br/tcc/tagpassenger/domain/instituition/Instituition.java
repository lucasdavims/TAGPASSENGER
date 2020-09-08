package com.br.tcc.tagpassenger.domain.instituition;

import com.br.tcc.tagpassenger.domain.passenger.Passenger;

import java.util.List;

/**
 * Created by Davi on 08/09/2020.
 */

public class Instituition {

    private Long id;
    private String name;
    private String address;
    List<Passenger> passengers;

    public Instituition() {
    }

    public Instituition(Long id, String name, String address, List<Passenger> passengers) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.passengers = passengers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
