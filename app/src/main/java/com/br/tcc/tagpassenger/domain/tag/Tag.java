package com.br.tcc.tagpassenger.domain.tag;

import com.br.tcc.tagpassenger.domain.passenger.Passenger;

/**
 * Created by Davi on 08/09/2020.
 */

public class Tag {

    private Long id;
    private String serialNumber;
    private Passenger passenger;

    public Tag() {
    }

    public Tag(Long id, String serialNumber) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.passenger = null;
    }

    public Tag(Long id, String serialNumber, Passenger passenger) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.passenger = passenger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
