package com.br.tcc.tagpassenger.domain.vehicle;

import com.br.tcc.tagpassenger.domain.motorist.Motorist;
import com.br.tcc.tagpassenger.domain.passenger.Passenger;

import java.util.List;

/**
 * Created by Davi on 08/09/2020.
 */

public class Vehicle {

    private Long id;
    private String plate;
    private String model;
    private Motorist motorist;

    public Vehicle() {
    }

    public Vehicle(Long id, String plate, String model, Motorist motorist) {
        this.id = id;
        this.plate = plate;
        this.model = model;
        this.motorist = motorist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Motorist getMotorist() {
        return motorist;
    }

    public void setMotorist(Motorist motorist) {
        this.motorist = motorist;
    }
}
