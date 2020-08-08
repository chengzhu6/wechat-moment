package com.thoughtworks.wechatmoments.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.thoughtworks.wechatmoments.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainViewModel mainViewModel;

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
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        mainViewModel.observerMomentDataLiveData(this, momentData -> {
            MainRecycleViewAdapter mainRecycleViewAdapter = new MainRecycleViewAdapter(momentData);
            recyclerView.setAdapter(mainRecycleViewAdapter);
        });
        fetchData();
    }

    private void fetchData() {
        mainViewModel.requestMomentData();
    }

    private void getView() {
        recyclerView = findViewById(R.id.moment_list);
    }
}