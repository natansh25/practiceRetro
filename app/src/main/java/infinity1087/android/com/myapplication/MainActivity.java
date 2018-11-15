package infinity1087.android.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import infinity1087.android.com.myapplication.adapter.NameAdapter;
import infinity1087.android.com.myapplication.model.Example;
import infinity1087.android.com.myapplication.model.Result;
import infinity1087.android.com.myapplication.service.ApiClient;
import infinity1087.android.com.myapplication.service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    NameAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        callingRetrofit();

    }

    private void callingRetrofit() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<Example> call = apiInterface.getMovies("popular", ApiClient.api_key);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<Result> results = response.body().getResults();

                mAdapter = new NameAdapter(results);
                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

    }
}
