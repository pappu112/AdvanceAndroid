package com.example.advanceandroid.di;

import android.app.Activity;
import android.content.Context;

import com.example.advanceandroid.base.BaseActivity;
import com.example.advanceandroid.base.MyApplication;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Provider;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;

public class ActivityInjector {

    private final Map<Class<? extends Activity>, javax.inject.Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors;
    private final Map<String, AndroidInjector<? extends  Activity>> cache = new HashMap<>();

    public ActivityInjector(Map<Class<? extends Activity>, Provider<AndroidInjector.Factory<? extends Activity>>> activityInjectors) {
        this.activityInjectors = activityInjectors;
    }

    void  inject(Activity activity){
        if(!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Activity must extend BaseActivity");
        }
        String instanceId = ((BaseActivity) activity).getInstanceId();
        if(cache.containsKey(instanceId)){
            //noinspection unchecked
            ((AndroidInjector<Activity>) cache.get(instanceId)).inject(activity);
            return;
        }
        //noinspection unchecked
        AndroidInjector.Factory<Activity> injectorFactory =
                (AndroidInjector.Factory<Activity>) activityInjectors.get(activity.getClass()).get();
        AndroidInjector<Activity> injector = injectorFactory.create(activity);
        cache.put(instanceId, injector);
        injector.inject(activity);
    }

    void clear(Activity activity){
        if(!(activity instanceof BaseActivity)){
            throw new IllegalArgumentException("Activity must be BaseActivity");
        }
        cache.remove(((BaseActivity)activity).getInstanceId());
    }
    static ActivityInjector get(Context context) {
        return ((MyApplication) context.getApplicationContext()).getActivityInjector();
    }


}
