package infinity1087.android.com.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;
import java.util.Observer;

import infinity1087.android.com.myapplication.adapter.NameAdapter;
import infinity1087.android.com.myapplication.model.Example;
import infinity1087.android.com.myapplication.model.Result;
import infinity1087.android.com.myapplication.service.ApiClient;
import infinity1087.android.com.myapplication.service.ApiInterface;
import infinity1087.android.com.myapplication.viewModel.NameViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    NameAdapter mAdapter;
    NameViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // view model

        mViewModel = ViewModelProviders.of(this).get(NameViewModel.class);


        mViewModel.getData().observe(this, new android.arch.lifecycle.Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> results) {

                setUpRecyclerView(results);

            }
        });


        callingRetrofit();

    }

    private void setUpRecyclerView(List<Result> results) {


        mAdapter = new NameAdapter(results, new NameAdapter.ListItemClickListner() {
            @Override
            public void onListItemClick(Result movieResults) {

                Toast.makeText(MainActivity.this, movieResults.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);


    }


    private void callingRetrofit() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Call<Example> call = apiInterface.getMovies("popular", ApiClient.api_key);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<Result> results = response.body().getResults();

                mAdapter = new NameAdapter(results, new NameAdapter.ListItemClickListner() {
                    @Override
                    public void onListItemClick(Result movieResults) {
                        Toast.makeText(MainActivity.this, movieResults.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {


            }
        });

    }


}
