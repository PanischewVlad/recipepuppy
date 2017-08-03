package com.samapps.receiptpuppie.other;

import android.app.Application;

import com.samapps.receiptpuppie.other.di.AppComponent;
import com.samapps.receiptpuppie.other.di.DaggerAppComponent;

public class App extends Application {

    public static AppComponent getComponent() {
        return buildComponent();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected static AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .build();
    }


}
