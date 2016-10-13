package com.rrajath.jackdaw.main;

import com.rrajath.jackdaw.util.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return activity;
    }

    @Provides
    @ActivityScope
    MainActivityPresenter provideMainActivityPresenter(MainActivity activity) {
        return new MainActivityPresenter(activity);
    }
}
