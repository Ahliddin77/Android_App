package uz.akhliddin.project4.repository;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import uz.akhliddin.project4.model.UserModel;

public interface Repository {

    public Flowable<List<UserModel>> getAllUsersFromDb();

    public Completable insertUserToDb(String name, int sum);

    public Completable clearAllUsersFromDb();

}
