package uz.akhliddin.project4.feature.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import uz.akhliddin.project4.databinding.ItemLyBinding;
import uz.akhliddin.project4.model.UserModel;
import uz.akhliddin.project4.utils.Utils;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    public List<UserModel> itemsList = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    public void setUsersList(List<UserModel> list) {
        itemsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLyBinding itemBinding = ItemLyBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new UsersViewHolder(itemBinding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        UserModel item = itemsList.get(position);
        final Context context = holder.itemBinding.getRoot().getContext();

        holder.itemBinding.dataId.setText(String.valueOf(item.getId()));
        holder.itemBinding.nameText.setText(item.getName());
        holder.itemBinding.sumText.setText(String.valueOf(item.getSum()));

        final String message = "name: " + item.getName() + "\n" + "sum: " + item.getSum();
        holder.itemBinding.getRoot().setOnClickListener(view -> Utils.showToast(context, message));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}

