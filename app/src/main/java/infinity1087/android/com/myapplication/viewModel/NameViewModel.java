package infinity1087.android.com.myapplication.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import infinity1087.android.com.myapplication.model.Result;
import infinity1087.android.com.myapplication.respository.Respository;

public class NameViewModel extends AndroidViewModel {


    LiveData<List<Result>> mLiveData;
    Respository mRespository;


    public NameViewModel(@NonNull Application application) {
        super(application);
        mRespository = new Respository();
    }


    public LiveData<List<Result>> getData() {
        mLiveData = mRespository.getData();
        return mLiveData;
    }
}
