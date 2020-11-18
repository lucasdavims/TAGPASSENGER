package com.br.tcc.tagpassenger.features.rfid;

import android.content.Context;

import com.br.tcc.tagpassenger.domain.passenger.Passenger;
import com.br.tcc.tagpassenger.domain.tag.Tag;
import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.storage.passenger.PassengerRepositorySQLite;
import com.br.tcc.tagpassenger.storage.tag.TagRepositorySQLite;
import com.br.tcc.tagpassenger.storage.trip.TripRepositorySQLite;

import java.util.ArrayList;
import java.util.List;

public class TreatTagUseCase {
    PassengerRepositorySQLite passengerRepository;
    TagRepositorySQLite tagRepository;
    TripRepositorySQLite tripRepository;
    String rfidTag;


    public TreatTagUseCase(Context context) {
        passengerRepository = PassengerRepositorySQLite.getInstance(context);
        tagRepository = TagRepositorySQLite.getInstance(context);
        tripRepository = TripRepositorySQLite.getInstance(context);
    }

    public Void execute() throws Exception {
        Tag tag = tagRepository.getTagBySerialNumber(this.rfidTag);
        if(tag != null){
            if(tag.getPassenger() != null){
                Trip trip = tripRepository.getCurrentTripByPassengerId(tag.getPassenger().getId());
                if(trip == null){
                    trip = tripRepository.getCurrentTrip();
                    if(trip != null){
                        if(trip.getTrip() != null){
                            // embarque volta
                            tripRepository.embarqueVoltaPassageiro(trip.getId(),tag.getPassenger().getId(),trip.getTrip().getId());
                        }else{
                            //embarque ida
                            tripRepository.embarqueIdaPassageiro(trip.getId(),tag.getPassenger().getId());
                        }

                    }
                }else{
                    //Implementação futura de tratamento caso não exista viagem vigente e a leitura da tag seja efetuada.
                }

            }else{
                //Tag Não está associada a nenhum passageiro
            }
        }

        return null;
    }

    String getRfidTag(){
        return this.rfidTag;
    }

    public void setRfidTag(String rfidTag){
        this.rfidTag = rfidTag;
    }
}
