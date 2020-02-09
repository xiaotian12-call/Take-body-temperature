package com.me.temperature;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TpViewModel extends AndroidViewModel {
    private TpRepository tpRepository;

    public TpViewModel(@NonNull Application application) {
        super(application);
        tpRepository = new TpRepository((application));
    }

    public LiveData<List<temperature>> getAllTemperature() {
        return tpRepository.getAllTemperature();
    }

    void insertTemperature(temperature ... temperatures){
        tpRepository.insertTemperature(temperatures);
    }
    void deleteAllTemperature(){
        tpRepository.deleteAllTemperature();
    }
    void deleteTemperature(temperature ... temperatures){
        tpRepository.deleteTemperature(temperatures);
    }
}
