package com.example.advanceandroid.base;

import android.app.Application;

import com.example.advanceandroid.di.ActivityInjector;

import javax.inject.Inject;

public class MyApplication extends Application {
    private ApplicationComponent component;
    @Inject
    ActivityInjector activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }


}
