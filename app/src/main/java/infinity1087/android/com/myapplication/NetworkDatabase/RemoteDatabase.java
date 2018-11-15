package infinity1087.android.com.myapplication.NetworkDatabase;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import infinity1087.android.com.myapplication.MainActivity;
import infinity1087.android.com.myapplication.adapter.NameAdapter;
import infinity1087.android.com.myapplication.model.Example;
import infinity1087.android.com.myapplication.model.Result;
import infinity1087.android.com.myapplication.service.ApiClient;
import infinity1087.android.com.myapplication.service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDatabase {

    public static MutableLiveData<List<Result>> movieResult = new MutableLiveData<>();


    public static void getResults(String sort) {
        Log.d("xxx","called");

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<Example> call = apiInterface.getMovies("popular", ApiClient.api_key);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<Result> results = response.body().getResults();


                movieResult.postValue(results);


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {


            }
        });


    }


    public static LiveData<List<Result>> getMovieData() {
        return movieResult;
    }


}
