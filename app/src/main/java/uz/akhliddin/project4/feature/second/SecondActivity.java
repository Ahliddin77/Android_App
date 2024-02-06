package uz.akhliddin.project4.feature.second;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uz.akhliddin.project4.databinding.ActivitySecondBinding;
import uz.akhliddin.project4.feature.adapter.UsersAdapter;
import uz.akhliddin.project4.model.UserModel;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    private SecondViewModel viewModel;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SecondViewModel.class);

        adapter = new UsersAdapter();
        final RecyclerView myRecycler = binding.recyclerView;

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        myRecycler.setLayoutManager(layoutManager);
        myRecycler.setAdapter(adapter);

        getAllUsersObserver();
    }

    private void getAllUsersObserver() {
        Observer<List<UserModel>> listObserver = users -> adapter.setUsersList(users);
        viewModel.getAllUsers().observe(this, listObserver);
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.showNotification();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}