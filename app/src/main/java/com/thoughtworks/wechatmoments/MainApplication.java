package com.thoughtworks.wechatmoments;

import android.app.Application;
import android.content.Context;

import com.thoughtworks.wechatmoments.repository.main.MomentRemoteDataSource;
import com.thoughtworks.wechatmoments.repository.main.MomentRepository;
import com.thoughtworks.wechatmoments.repository.main.MomentRepositoryImpl;

public class MainApplication extends Application {

    private Context applicationContext;

    private MomentRepository momentRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }

    public MomentRepository momentRepository() {
        if (momentRepository == null) {
            momentRepository = new MomentRepositoryImpl(new MomentRemoteDataSource());
        }
        return momentRepository;
    }
}
