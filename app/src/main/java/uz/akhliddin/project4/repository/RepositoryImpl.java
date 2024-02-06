package uz.akhliddin.project4.repository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import uz.akhliddin.project4.data.UserDao;
import uz.akhliddin.project4.model.UserModel;

public class RepositoryImpl implements Repository {
    private final UserDao dao;

    public RepositoryImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public Flowable<List<UserModel>> getAllUsersFromDb() {
        return dao.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable insertUserToDb(String name, int sum) {
        final UserModel model = new UserModel(name, sum);
        return dao.insertData(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable clearAllUsersFromDb() {
        return dao.clearAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
