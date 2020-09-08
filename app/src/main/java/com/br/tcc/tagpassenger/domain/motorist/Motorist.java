package com.br.tcc.tagpassenger.domain.motorist;

import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;

import java.util.List;

/**
 * Created by Davi on 08/09/2020.
 */

public class Motorist {

    private Long id;
    private String cpf;
    private String rg;
    private String cnh;
    private List<Vehicle> vehicles;

    public Motorist() {
    }

    public Motorist(Long id, String cpf, String rg, String cnh, List<Vehicle> vehicles) {
        this.id = id;
        this.cpf = cpf;
        this.rg = rg;
        this.cnh = cnh;
        this.vehicles = vehicles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
