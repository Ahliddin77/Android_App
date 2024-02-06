package uz.akhliddin.project4.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import uz.akhliddin.project4.model.UserModel;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users_table")
    Flowable<List<UserModel>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertData(UserModel user);

    @Query("DELETE FROM users_table")
    Completable clearAllData();

}
