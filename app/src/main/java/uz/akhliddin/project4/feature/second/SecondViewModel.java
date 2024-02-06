package uz.akhliddin.project4.feature.second;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import uz.akhliddin.project4.data.AppDatabase;
import uz.akhliddin.project4.model.UserModel;
import uz.akhliddin.project4.repository.Repository;
import uz.akhliddin.project4.repository.RepositoryImpl;
import uz.akhliddin.project4.utils.OnStopNotification;
import uz.akhliddin.project4.utils.StopCounter;

public class SecondViewModel extends AndroidViewModel {

    private final Repository repository;
    private final OnStopNotification notificationsManager;

    public SecondViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application.getApplicationContext());
        this.repository = new RepositoryImpl(appDatabase.myDao());
        this.notificationsManager = new OnStopNotification(application.getApplicationContext());
    }

    public LiveData<List<UserModel>> getAllUsers() {
        return LiveDataReactiveStreams.fromPublisher(repository.getAllUsersFromDb());
    }

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void showNotification() {
        StopCounter.incrementMainStop();
        final Disposable disposable = StopCounter.countingOnStop()
                .subscribe(notificationsManager::updateNotification);

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
