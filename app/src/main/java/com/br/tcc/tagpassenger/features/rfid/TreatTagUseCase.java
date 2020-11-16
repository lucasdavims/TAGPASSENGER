package com.br.tcc.tagpassenger.features.rfid;

import android.content.Context;

import com.br.tcc.tagpassenger.domain.tag.Tag;
import com.br.tcc.tagpassenger.domain.trip.Trip;
import com.br.tcc.tagpassenger.storage.passenger.PassengerRepositorySQLite;
import com.br.tcc.tagpassenger.storage.tag.TagRepositorySQLite;
import com.br.tcc.tagpassenger.storage.trip.TripRepositorySQLite;

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
                if(trip != null){
                    if(trip.getTrip() != null){
                        // embarque volta
                        //adicionar passageiro a viagem atual
                        //

                        /*
                         */
                    }else{
                        //embarque ida

                        /*

                        */
                    }
                }else{
                    //realizar embarque e em seguida marcar presença
                }

            }
        }

        return null;
    }

    String getRfidTag(){
        return this.rfidTag;
    }

    void setRfidTag(String rfidTag){
        this.rfidTag = rfidTag;
    }
}
