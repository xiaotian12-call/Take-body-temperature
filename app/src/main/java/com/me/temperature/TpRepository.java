package com.me.temperature;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TpRepository {
    private LiveData<List<temperature>> allTemperature;
    private static List<temperature> allTemperatures;

    private TempDao tempDao;
    public TpRepository(Context context) {
        TempDatabase accountRecordDatabase = TempDatabase.getTempDatabase(context.getApplicationContext());
        tempDao = accountRecordDatabase.getTempDao();
        allTemperature = tempDao.getAllTp();
    }

    public LiveData<List<temperature>> getAllTemperature() {
        return allTemperature;
    }
    void insertTemperature(temperature ... temperatures){
        new InsertAsyncTask(tempDao).execute(temperatures);
    }
    void deleteAllTemperature(){
        new DeleteAsyncTask(tempDao).execute();
    }
    void deleteTemperature(temperature ... temperatures){
        new DeleteAsyncTask_2(tempDao).execute(temperatures);
    }
    static class InsertAsyncTask extends AsyncTask<temperature ,Void,Void> {
        private TempDao tempDao;

        public InsertAsyncTask(TempDao tempDao) {
            this.tempDao = tempDao;
        }

        @Override
        protected Void doInBackground(temperature... temperatures) {
            tempDao.insertTp(temperatures);
            return null;
        }
    }
    static class DeleteAsyncTask extends AsyncTask<Void ,Void,Void>{
        private TempDao tempDao;

        public DeleteAsyncTask(TempDao tempDao) {
            this.tempDao = tempDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            tempDao.deleteAllTp();
            return null;
        }
    }
    static class DeleteAsyncTask_2 extends AsyncTask<temperature ,Void,Void>{
        private TempDao tempDao;

        public DeleteAsyncTask_2(TempDao tempDao) {
            this.tempDao = tempDao;
        }

        @Override
        protected Void doInBackground(temperature... temperatures ) {
            tempDao.deleteTp(temperatures);
            return null;
        }
    }
}
