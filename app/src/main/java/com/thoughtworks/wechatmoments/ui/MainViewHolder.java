package com.thoughtworks.wechatmoments.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.wechatmoments.model.MomentData;

public abstract class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void setData(MomentData momentData);
    }