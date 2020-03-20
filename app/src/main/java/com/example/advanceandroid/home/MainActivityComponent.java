package com.example.advanceandroid.home;

import com.example.advanceandroid.di.ActivityAcope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityAcope
@Subcomponent
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }

}
