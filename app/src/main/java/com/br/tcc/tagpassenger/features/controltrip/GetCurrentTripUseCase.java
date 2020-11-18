package com.br.tcc.tagpassenger.features.controltrip;

import android.content.Context;

import com.br.tcc.tagpassenger.domain.instituition.Instituition;
import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.tag.Tag;
import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;
import com.br.tcc.tagpassenger.storage.passenger.PassengerRepositorySQLite;
import com.br.tcc.tagpassenger.storage.trip.TripRepositorySQLite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Davi on 01/11/2020.
 */

public class GetCurrentTripUseCase {

    TripRepositorySQLite tripRepository;
    PassengerRepositorySQLite passengerRepository;

    public GetCurrentTripUseCase(Context context) {
        tripRepository = TripRepositorySQLite.getInstance(context);
        passengerRepository = PassengerRepositorySQLite.getInstance(context);
    }

    public CurrentTripDto execute(){

        Trip trip = tripRepository.getCurrentTrip();

        List<Passenger> passengersActual = passengerRepository.getByTripWithRelationships(trip);

        List<Passenger> passengersPast = new ArrayList<>();
        List<Passenger> listaFinal = new ArrayList<>();
        if(trip.getTrip() != null){
            passengersPast = passengerRepository.getByTripWithRelationships(trip.getTrip());
        }

            for (Passenger passengerAnterior : passengersPast) {
                boolean flag = false;
                for (Passenger passengerAtual : passengersActual) {
                    if(passengerAtual.getId() == passengerAnterior.getId()){
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    passengerAnterior.setLanding(false);
                    passengersActual.add(passengerAnterior);
                }
        }
            /*if(passengersActual.size() == 0){
                passengersActual.addAll(passengersPast);
            }*/

        CurrentTripDto currentTrip = new CurrentTripDto();
        currentTrip.setTrip(trip);
        currentTrip.setPassengers(passengersActual);



        //disableMockAfterFinish
       // return mockDataTest();

        //enableAfterFinish
        return currentTrip;
    }


}
