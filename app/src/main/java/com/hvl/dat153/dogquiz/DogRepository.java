package com.hvl.dat153.dogquiz;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import android.app.Application;

public class DogRepository {

    private MutableLiveData<List<Dog>> searchResults = new MutableLiveData<>();
    private List<Dog> allDogs;
    private DogDao dogDao;

    public DogRepository(Application application) {
        DogRoomDB db;
        db = DogRoomDB.getDatabase(application);
        dogDao = db.dogDao();
        allDogs = dogDao.getAllDogs();
    }

    public void insertDog (Dog newDog) {
        InsertAsyncTask task = new InsertAsyncTask(dogDao);
        task.execute(newDog);
    }

    public void deleteDog (int id) {
        DeleteAsyncTask task = new DeleteAsyncTask(dogDao);
        task.execute(id);
    }

    public void findDog (String name) {
        QueryAsyncTask task = new QueryAsyncTask(dogDao);
        task.delegate = this;
        task.execute(name);
    }

    public MutableLiveData<List<Dog>> getSearchResults() {
        return searchResults;
    }

    private void asyncFinished(List<Dog> results) {
        searchResults.setValue(results);
    }

    public void addDog(Dog dog) {
        insertDog(dog);
    }

    public void deleteId(int id) {
        deleteDog(id);
    }

    public List<Dog> getAllDogs() {
        return allDogs;
    }

    private static class QueryAsyncTask extends AsyncTask<String, Void, List<Dog>> {
        private DogDao asyncTaskDao;
        private DogRepository delegate = null;

        QueryAsyncTask(DogDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<Dog> doInBackground(final String... params) {
            return asyncTaskDao.findDog(params[0]);
        }

        @Override
        protected void onPostExecute(List<Dog> result) {
            delegate.asyncFinished(result);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Dog, Void, Void> {
        private DogDao asyncTaskDao;
        InsertAsyncTask(DogDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Dog... params) {
            asyncTaskDao.insertDog(params[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private DogDao asyncTaskDao;
        DeleteAsyncTask(DogDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Integer... params) {
            asyncTaskDao.deleteDog(params[0]);
            return null;
        }
    }
}
