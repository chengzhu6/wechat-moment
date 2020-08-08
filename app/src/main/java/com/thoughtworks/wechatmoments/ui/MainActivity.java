package com.thoughtworks.wechatmoments.ui;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thoughtworks.wechatmoments.MainApplication;
import com.thoughtworks.wechatmoments.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getView();
        obtainViewModel();
        init();
    }

    private void obtainViewModel() {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        MainApplication application = (MainApplication) getApplication();
        mainViewModel.setMomentRepository(application.momentRepository());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainRecycleViewAdapter mainRecycleViewAdapter = new MainRecycleViewAdapter();
        recyclerView.setAdapter(mainRecycleViewAdapter);
        recyclerView.setHasFixedSize(true);

        mainViewModel.observerProfileLiveData(this, mainRecycleViewAdapter::insertProfile);
        mainViewModel.observerTweetsLiveData(this, mainRecycleViewAdapter::insertTweets);
        fetchData();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void fetchData() {
        mainViewModel.requestProfile();
        mainViewModel.requestTweets();
    }

    private void getView() {
        recyclerView = findViewById(R.id.moment_list);
    }
}