package com.rrajath.jackdaw;

import android.app.Application;
import android.content.Context;

public class JackdawApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static JackdawApplication get(Context context) {
        return (JackdawApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
