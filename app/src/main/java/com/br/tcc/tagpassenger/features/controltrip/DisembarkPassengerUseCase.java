package com.br.tcc.tagpassenger.features.controltrip;

import android.content.Context;

import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.storage.trip.TripRepositorySQLite;

/**
 * Created by Davi on 17/11/2020.
 */

public class DisembarkPassengerUseCase {

    private TripRepositorySQLite tripRepository;

    private Passenger passenger;

    public DisembarkPassengerUseCase(Context context) {
        this.tripRepository = TripRepositorySQLite.getInstance(context);
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setPassengerId(int id){
        this.passenger = new Passenger(Long.valueOf(id));
    }

    /**
     * This method is responsible for disembarking a passenger from the current trip.
     * @return
     * @throws Exception
     */
    public Void execute() throws Exception {

        if(passenger == null)
            throw new Exception("Erro ao desembarcar passageiro não presente");

        Trip trip = tripRepository.getCurrentTrip();

        if(trip != null){
            int result = tripRepository.disembarkPassenger(trip,passenger);

            if (result <= 0)
                throw new Exception("Erro ao desembarcar");
        }else{
            throw new Exception("Viagem corrente inexistente");
        }



        return null;
    }
}
