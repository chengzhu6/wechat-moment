package com.thoughtworks.wechatmoments.repository.main;

import com.thoughtworks.wechatmoments.repository.main.entity.ProfileResponse;
import com.thoughtworks.wechatmoments.repository.main.entity.TweetResponse;

import java.util.List;

import io.reactivex.Maybe;

public class MomentRepositoryImpl implements MomentRepository {

    private MomentDataSource momentDataSource;

    public MomentRepositoryImpl(MomentDataSource momentDataSource) {
        this.momentDataSource = momentDataSource;
    }

    @Override
    public Maybe<ProfileResponse> getProfile() {
        return momentDataSource.getProfile();
    }

    @Override
    public Maybe<List<TweetResponse>> getTweets() {
        return momentDataSource.getTweets();
    }
}
