package com.br.tcc.tagpassenger.domain.passenger;

import com.br.tcc.tagpassenger.domain.instituition.Instituition;
import com.br.tcc.tagpassenger.domain.tag.Tag;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;

/**
 * Created by Davi on 08/09/2020.
 */

public class Passenger {

    private Long id;
    private String cpf;
    private String rg;
    private String name;
    private Instituition instituition;
    private Vehicle vehicle;
    private Tag tag;

    public Passenger() {
    }

    public Passenger(Long id, String cpf, String rg, String name, Instituition instituition, Vehicle vehicle, Tag tag) {
        this.id = id;
        this.cpf = cpf;
        this.rg = rg;
        this.name = name;
        this.instituition = instituition;
        this.vehicle = vehicle;
        this.tag = tag;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instituition getInstituition() {
        return instituition;
    }

    public void setInstituition(Instituition instituition) {
        this.instituition = instituition;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
