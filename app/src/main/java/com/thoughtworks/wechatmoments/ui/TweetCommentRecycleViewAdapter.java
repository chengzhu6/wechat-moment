package com.thoughtworks.wechatmoments.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.wechatmoments.R;
import com.thoughtworks.wechatmoments.model.Comment;

import java.util.List;

public class TweetCommentRecycleViewAdapter extends RecyclerView.Adapter<TweetCommentRecycleViewAdapter.TweetCommentItemViewHolder> {

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    private List<Comment> comments;
    @NonNull
    @Override
    public TweetCommentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_comments_item, parent, false);
        return new TweetCommentItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetCommentItemViewHolder holder, int position) {
        holder.setData(comments.get(position));;
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class TweetCommentItemViewHolder extends RecyclerView.ViewHolder{

        private TextView usernameTV;
        private TextView contentTV;

        public TweetCommentItemViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTV = itemView.findViewById(R.id.comment_username);
            contentTV = itemView.findViewById(R.id.comment_content);
        }

        public void setData(Comment comment) {
            usernameTV.setText(String.format("%s:", comment.getSenderName()));
            contentTV.setText(comment.getContent());
        }
    }
}
