package uz.akhliddin.project4.feature.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import uz.akhliddin.project4.data.AppDatabase;
import uz.akhliddin.project4.repository.Repository;
import uz.akhliddin.project4.repository.RepositoryImpl;
import uz.akhliddin.project4.utils.OnStopNotification;
import uz.akhliddin.project4.utils.StopCounter;

public class MainViewModel extends AndroidViewModel {
    private final Repository repository;
    private final OnStopNotification notificationsManager;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application.getApplicationContext());
        this.repository = new RepositoryImpl(appDatabase.myDao());
        this.notificationsManager = new OnStopNotification(application.getApplicationContext());
    }

    public Completable insertNewUser(String name, int sum) {
        return repository.insertUserToDb(name, sum);
    }

    public Completable clearAllUsers() {
        return repository.clearAllUsersFromDb();
    }

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void showNotification() {
        StopCounter.incrementMainStop();
        final Disposable disposable = StopCounter.countingOnStop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notificationsManager::updateNotification);

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

}
