package com.rrajath.jackdaw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
    }

    @Override
    protected void onDestroy() {
//        closeRealm();
        super.onDestroy();
    }

//    protected abstract void closeRealm();
    protected abstract void setupActivityComponent();
}
