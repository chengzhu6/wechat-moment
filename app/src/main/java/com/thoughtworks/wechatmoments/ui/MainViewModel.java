package com.thoughtworks.wechatmoments.ui;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.wechatmoments.model.MomentData;
import com.thoughtworks.wechatmoments.model.Tweet;
import com.thoughtworks.wechatmoments.repository.main.MomentRepository;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainViewModel extends ViewModel {
    private MutableLiveData<List<MomentData>> tweetsLiveData;
    private MutableLiveData<MomentData> profileMomentDataLiveData;
    private MomentRepository momentRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void setMomentRepository(MomentRepository momentRepository) {
        this.momentRepository = momentRepository;
    }

    private MutableLiveData<List<MomentData>> getTweetsLiveData() {
        if (tweetsLiveData == null) {
            tweetsLiveData = new MutableLiveData<>();
        }
        return tweetsLiveData;
    }

    public void observerTweetsLiveData(LifecycleOwner lifecycleOwner, Observer<List<MomentData>> observer) {
        getTweetsLiveData().observe(lifecycleOwner, observer);
    }

    public void observerProfileLiveData(LifecycleOwner lifecycleOwner, Observer<MomentData> observer) {
        getProfileLiveData().observe(lifecycleOwner, observer);
    }

    private MutableLiveData<MomentData> getProfileLiveData() {
        if (profileMomentDataLiveData == null) {
            profileMomentDataLiveData = new MutableLiveData<>();
        }
        return profileMomentDataLiveData;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    public void requestProfile() {
        Disposable disposable = momentRepository.getProfile()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .doOnSuccess(profileResponse -> profileMomentDataLiveData
                        .postValue(MomentData.createProfileMomentData(profileResponse)))
                .subscribe();
        compositeDisposable.add(disposable);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void requestTweets() {
        Disposable disposable = momentRepository.getTweets()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(compositeDisposable::add)
                .doOnSuccess(tweetResponses -> tweetsLiveData.postValue(tweetResponses.stream()
                        .filter(tweetResponse -> tweetResponse.getSender() != null)
                        .map(Tweet::fromTweetResponse)
                        .map(MomentData::fromTweet)
                        .collect(Collectors.toList())))
                .subscribe();
        compositeDisposable.add(disposable);
    }
}
