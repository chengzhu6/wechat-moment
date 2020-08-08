package com.thoughtworks.wechatmoments.repository.main;

import com.thoughtworks.wechatmoments.model.Tweet;
import com.thoughtworks.wechatmoments.repository.main.entity.ProfileResponse;
import com.thoughtworks.wechatmoments.repository.main.entity.TweetResponse;

import java.util.List;

import io.reactivex.Maybe;

public interface MomentRepository {

    Maybe<ProfileResponse> getProfile();

    Maybe<List<TweetResponse>> getTweets();
}
