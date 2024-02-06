package uz.akhliddin.project4;

import android.app.Application;

import uz.akhliddin.project4.data.AppDatabase;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase.getInstance(this);
    }
}
