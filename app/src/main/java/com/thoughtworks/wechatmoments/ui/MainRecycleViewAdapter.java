package com.thoughtworks.wechatmoments.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.wechatmoments.R;
import com.thoughtworks.wechatmoments.model.MomentData;

import java.util.List;

public class MainRecycleViewAdapter extends RecyclerView.Adapter {

    private List<MomentData> momentData;

    public MainRecycleViewAdapter(@NonNull List<MomentData> momentData) {
        this.momentData = momentData;
    }

    @Override
    public int getItemViewType(int position) {
        return momentData.get(position).getType().getValue();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_header, parent, false);
        return new ProfileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.momentData.size();
    }

    private class ProfileViewHolder extends RecyclerView.ViewHolder {
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class TweetViewHolder extends RecyclerView.ViewHolder {
        public TweetViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
