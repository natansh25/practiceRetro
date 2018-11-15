package infinity1087.android.com.myapplication.respository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import infinity1087.android.com.myapplication.NetworkDatabase.RemoteDatabase;
import infinity1087.android.com.myapplication.model.Result;

public class Respository {

    LiveData<List<Result>> data;

    public Respository() {


        RemoteDatabase.getResults("popular");

    }

    public LiveData<List<Result>> getData() {
        data = RemoteDatabase.getMovieData();
        return data;
    }


}
