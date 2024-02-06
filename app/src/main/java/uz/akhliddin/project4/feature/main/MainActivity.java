package uz.akhliddin.project4.feature.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import io.reactivex.rxjava3.core.Completable;
import uz.akhliddin.project4.databinding.ActivityMainBinding;
import uz.akhliddin.project4.feature.second.SecondActivity;
import uz.akhliddin.project4.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        nameEditWatcher();
        sumOfArgumentsWatchers();

        binding.displayButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        binding.addButton.setOnClickListener(view -> addNewUser());

        binding.clearButton.setOnClickListener(view -> viewModel.clearAllUsers()
                .andThen(Completable.fromRunnable(() -> Utils.showToast(this, "All data cleared!")))
                .subscribe()
        );
    }

    private void addNewUser() {
        final String labelName = binding.labelName.getText().toString();
        final String sumOfArgs = binding.labelSumOfArgs.getText().toString();
        if (!labelName.equals("none") && !sumOfArgs.equals("0")) {
            viewModel.insertNewUser(labelName, Integer.parseInt(sumOfArgs)).
                    andThen(Completable.fromRunnable(() -> Utils.showToast(this, "Added")))
                    .subscribe();
        } else {
            Utils.showToast(this, "fill in the fields!");
        }
    }

    private void nameEditWatcher() {
        binding.nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String nameEditText = binding.nameEditText.getText().toString();

                if (nameEditText.isEmpty()) {
                    binding.labelName.setText("none");
                } else {
                    binding.labelName.setText(nameEditText);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void sumOfArgumentsWatchers() {
        // watcher argument1 edit text
        binding.firstArgument.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String firstArgumentValue = binding.firstArgument.getText().toString();
                final String secondArgumentValue = binding.secondArgument.getText().toString();

                if (!firstArgumentValue.isEmpty() && !secondArgumentValue.isEmpty()) {
                    incrementArguments(
                            Integer.parseInt(firstArgumentValue),
                            Integer.parseInt(secondArgumentValue)
                    );
                } else if (firstArgumentValue.isEmpty() && secondArgumentValue.isEmpty()) {
                    binding.labelSumOfArgs.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        // watcher secondArgument edit text
        binding.secondArgument.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String secondArgumentValue = binding.secondArgument.getText().toString();
                final String firstArgumentValue = binding.firstArgument.getText().toString();

                if (!firstArgumentValue.isEmpty() && !secondArgumentValue.isEmpty()) {
                    incrementArguments(
                            Integer.parseInt(firstArgumentValue),
                            Integer.parseInt(secondArgumentValue)
                    );
                } else if (firstArgumentValue.isEmpty() && secondArgumentValue.isEmpty()) {
                    binding.labelSumOfArgs.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void incrementArguments(int arg1, int arg2) {
        final int sumOfArguments = arg1 + arg2;
        binding.labelSumOfArgs.setText(String.valueOf(sumOfArguments));
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewModel.showNotification();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Utils.getNotificationPermission(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}