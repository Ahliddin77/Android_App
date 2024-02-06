package uz.akhliddin.project4.feature.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import uz.akhliddin.project4.databinding.ItemLyBinding;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    public ItemLyBinding itemBinding;

    public UsersViewHolder(@NonNull ItemLyBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
    }
}
