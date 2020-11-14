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

        int x = 0;
        for (Passenger p:
             passengersActual) {

            if(x == 3){
                p.setLanding(true);
            }

            if(x == 4){
                p.setLanding(true);
            }

            if(x == 5){
                p.setLanding(true);
            }

            if(x == 6){
                p.setLanding(true);
            }


            p.setPresentGoing(true);


            p.setPresentBack(true);

            x++;
        }

        //Make a check presence
        /*
        List<Passenger> passengersPast = new ArrayList<>();

        List<Passenger> passengersResult = new ArrayList<>();;


        if (trip.getTrip() != null) {
            passengersPast = passengerRepository.getByTripWithRelationships(new Trip(trip.getTrip().getId()));
            for (Passenger p : passengersActual) {
                p.setPresentGoing(passengersPast.contains(p));
                p.setPresentBack(true);

            }
            for (Passenger p : passengersPast){
                passengersActual.contains(p)
                p.setPresentBack();
            }
        }*/

        CurrentTripDto currentTrip = new CurrentTripDto();
        currentTrip.setTrip(trip);
        currentTrip.setPassengers(passengersActual);



        //disableMockAfterFinish
       // return mockDataTest();

        //enableAfterFinish
        return currentTrip;
    }

    public CurrentTripDto mockDataTest(){
        CurrentTripDto currentTrip = new CurrentTripDto();

        Passenger passenger1 = new Passenger(1L,
                "837272332",
                "111",
                "Lucas Davi Matias Siqueira",
                new Instituition(1L,
                        "Uvv",
                        "R. peralta santos, 1001",
                        null),
                new Tag());
        passenger1.setPresentGoing(true);


        Passenger passenger2 = new Passenger(1L,
                "53323423",
                "222",
                "Marcelo Pimentel",
                new Instituition(
                        1L,
                        "Faesa",
                        "Av. vitoria, 1302",
                        null),
                new Tag());
        passenger2.setPresentGoing(true);

        Passenger passenger3 = new Passenger(1L,
                "53323423",
                "222",
                "Raquel Emanuelle Leite Reis",
                new Instituition(
                        1L,
                        "Multivix",
                        "Av. Fernando Ferrari, 204",
                        null),
                new Tag());

        currentTrip.setPassengers(Arrays.asList(
                passenger1,
                passenger2,
                passenger3

        ));

        currentTrip.setTrip(
                new Trip(1L,
                        new Date(),
                        new Date(),
                        new Vehicle(
                                1L,
                                "12312",
                                "12312",
                                null),
                        null, null));

        return currentTrip;
    }
}
