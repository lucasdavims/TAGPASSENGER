package com.br.tcc.tagpassenger.domain.vehicle;

import com.br.tcc.tagpassenger.domain.motorist.Motorist;
import com.br.tcc.tagpassenger.domain.passenger.Passenger;

import java.util.List;

/**
 * Created by Davi on 08/09/2020.
 */

public class Vehicle {

    private Long id;
    private String placa;
    private String modelo;
    private Motorist motorist;

    public Vehicle() {
    }

    public Vehicle(Long id, String placa, String modelo, Motorist motorist) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.motorist = motorist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Motorist getMotorist() {
        return motorist;
    }

    public void setMotorist(Motorist motorist) {
        this.motorist = motorist;
    }
}
