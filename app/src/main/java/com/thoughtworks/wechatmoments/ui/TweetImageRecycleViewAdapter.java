package com.thoughtworks.wechatmoments.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thoughtworks.wechatmoments.R;

import java.util.List;

import static com.thoughtworks.wechatmoments.ui.TweetImageRecycleViewAdapter.*;

public class TweetImageRecycleViewAdapter extends RecyclerView.Adapter<TweetImageViewHolder> {

    private List<String> images;

    public TweetImageRecycleViewAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public TweetImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_item_images_item, parent, false);
        return new TweetImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetImageViewHolder holder, int position) {
        String path = images.get(position);
        holder.setData(path);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class TweetImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public TweetImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tweet_image);
        }

        public void setData(String path) {
            Glide.with(itemView)
                    .load(path)
                    .into(imageView);
        }
    }
}
