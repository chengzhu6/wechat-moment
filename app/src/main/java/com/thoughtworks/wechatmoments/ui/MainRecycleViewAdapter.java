package com.thoughtworks.wechatmoments.ui;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.thoughtworks.wechatmoments.R;
import com.thoughtworks.wechatmoments.model.MomentData;
import com.thoughtworks.wechatmoments.model.MomentData.MomentDataType;
import com.thoughtworks.wechatmoments.model.Profile;
import com.thoughtworks.wechatmoments.model.Tweet;

import java.util.ArrayList;
import java.util.List;

public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<MomentData> momentData;

    public MainRecycleViewAdapter() {
        this.momentData = new ArrayList<>();
    }

    public void insertProfile(MomentData profileMomentData) {
        momentData.add(0, profileMomentData);
        notifyItemInserted(0);
    }

    public void insertTweets(List<MomentData> tweets) {
        momentData.addAll(tweets);
        notifyItemRangeInserted(1, tweets.size());
    }

    @Override
    public int getItemViewType(int position) {
        return momentData.get(position).getType().getValue();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MomentDataType.PROFILE.getValue()) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_header, parent, false);
            return new ProfileViewHolder(itemView);
        }
        if (viewType == MomentDataType.TWEET.getValue()) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweets_item, parent, false);
            return new TweetViewHolder(itemView);
        }
        throw new IllegalArgumentException(String.format("ViewType %d not found", viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        MomentData momentData = this.momentData.get(position);
        holder.setData(momentData);
    }

    @Override
    public int getItemCount() {
        return this.momentData.size();
    }

    private static class ProfileViewHolder extends MainViewHolder {
        private ImageView backgroundIV;
        private TextView usernameTV;
        private ImageView faceIV;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroundIV = itemView.findViewById(R.id.profile_background_image);
            usernameTV = itemView.findViewById(R.id.profile_name);
            faceIV = itemView.findViewById(R.id.profile_face);
        }

        @Override
        public void setData(MomentData momentData) {
            Profile data = (Profile) momentData.getData();
            Glide.with(itemView)
                    .load(data.getBackgroundImage())
                    .into(backgroundIV);

            Glide.with(itemView)
                    .load(data.getAvatar())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(faceIV);

            usernameTV.setText(data.getName());
        }

    }

    private static class TweetViewHolder extends MainViewHolder {
        private ImageView tweetSenderFaceIV;
        private TextView tweetSenderUsernameTV;
        private TextView tweetContentTV;
        private RecyclerView tweetImagesRecycleView;
        private TextView tweetSendDateTV;

        public TweetViewHolder(@NonNull View itemView) {
            super(itemView);
            tweetSenderFaceIV = itemView.findViewById(R.id.tweet_user_face);
            tweetSenderUsernameTV = itemView.findViewById(R.id.tweet_user_name);
            tweetContentTV = itemView.findViewById(R.id.tweet_content);
            tweetImagesRecycleView = itemView.findViewById(R.id.tweet_images);
            tweetSendDateTV = itemView.findViewById(R.id.tweet_date);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void setData(MomentData momentData) {
            Tweet data = (Tweet) momentData.getData();
            Glide.with(itemView)
                    .load(data.getAvatar())
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(tweetSenderFaceIV);
            tweetSenderUsernameTV.setText(data.getUsername());
            String content = data.getContent();
            if (content != null) {
                tweetContentTV.setVisibility(View.VISIBLE);
                tweetContentTV.setText(content);
            }
            tweetSendDateTV.setText(R.string.tweet_send_date);
            List<String> imagesPath = data.getImagesPath();
            if (imagesPath != null) {
                tweetImagesRecycleView.setAdapter(new TweetImageRecycleViewAdapter(imagesPath));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(itemView.getContext(), 3);
                tweetImagesRecycleView.setLayoutManager(gridLayoutManager);

                tweetImagesRecycleView.setHasFixedSize(true);
            }
        }
    }

}
