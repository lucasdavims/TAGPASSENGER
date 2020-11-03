package com.br.tcc.tagpassenger.features.controltrip;

import android.content.Context;

import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.domain.vehicle.Vehicle;
import com.br.tcc.tagpassenger.storage.trip.TripRepositorySQLite;

import java.util.Date;

/**
 * Created by Davi on 02/11/2020.
 */
public class StartNewTripUseCase {

    TripRepositorySQLite tripRepository;

    public StartNewTripUseCase(Context context) {
        tripRepository = TripRepositorySQLite.getInstance(context);

    }

    //This method stop previous trip and start new, but if previous is a going trip this start a return and conversely
    public Void execute() throws Exception {

        Trip tripToFinish = tripRepository.getCurrentTrip();
        tripToFinish.setEnd(new Date());

        int result = tripRepository.update(tripToFinish);

        if(result > 0)
        result = tripRepository.persist(buildNewTrip(tripToFinish.getVehicle())).getId().intValue();

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
