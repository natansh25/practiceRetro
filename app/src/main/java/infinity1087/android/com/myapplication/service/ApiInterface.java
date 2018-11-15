package infinity1087.android.com.myapplication.service;

import infinity1087.android.com.myapplication.model.Example;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("movie/{filter}")
    Call<Example> getMovies(@Path("filter") String filter, @Query("api_key") String apiKey);

}
