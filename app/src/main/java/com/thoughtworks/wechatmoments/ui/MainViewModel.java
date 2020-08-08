package com.thoughtworks.wechatmoments.ui;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.thoughtworks.wechatmoments.model.MomentData;

import java.util.List;


public class MainViewModel extends ViewModel {
    private MutableLiveData<List<MomentData>> momentDataLiveData;

    private MutableLiveData<List<MomentData>> getMomentDataLiveData() {
        if (momentDataLiveData == null) {
            momentDataLiveData = new MutableLiveData<>();
        }
        return momentDataLiveData;
    }

    public void observerMomentDataLiveData(LifecycleOwner lifecycleOwner, Observer<List<MomentData>> observer) {
        getMomentDataLiveData().observe(lifecycleOwner, observer);
    }

    public void requestMomentData() {

    }
}
