package com.br.tcc.tagpassenger.features.controltrip;

import android.content.Context;

import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;
import com.br.tcc.tagpassenger.storage.trip.TripRepositorySQLite;
import com.br.tcc.tagpassenger.storage.vehicle.VehicleRepositorySQLite;

import java.util.Date;

/**
 * Created by Davi on 02/11/2020.
 */
public class StartNewTripUseCase {

    TripRepositorySQLite tripRepository;
    VehicleRepositorySQLite vehicleRepository;

    public StartNewTripUseCase(Context context) {
        tripRepository = TripRepositorySQLite.getInstance(context);
        vehicleRepository = VehicleRepositorySQLite.getInstance(context);

    }

    //This method stop previous trip and start new, but if previous is a going trip this start a return and conversely
    public Void execute() throws Exception {
        int result = 0;
        Trip trip = tripRepository.getCurrentTrip();
        if(trip != null){//existe viagem em andamento
            trip.setEnd(new Date());//finaliza viagem
            result = tripRepository.update(trip);

            Trip newTrip = buildNewTrip(trip.getVehicle());
            if(trip.getTrip() == null){
                newTrip.setTrip(trip);
            }

            if(result > 0)
                result = tripRepository.persist(newTrip).getId().intValue();
        }else{
            Vehicle vehicle = vehicleRepository.getVeiculo();
            result = tripRepository.persist(buildNewTrip(vehicle)).getId().intValue();
        }

        if(result <=0 )
            throw new Exception("Error to execute use case start trip");

        return null;
    }

    private Trip buildNewTrip(Vehicle previousVehicle){

        Trip newTrip = new Trip();
        newTrip.setBegin(new Date());
        newTrip.setVehicle(previousVehicle);

        return newTrip;
    };
}
