package com.jofficial.puretaka;

import android.app.Application;

import co.notix.interstitial.InterstitialLoader;
import co.notix.interstitial.NotixInterstitial;

public class App extends Application {
    static InterstitialLoader interstitialLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        /* ... */
//        interstitialLoader = NotixInterstitial.Companion.createLoader(6786700);
//        interstitialLoader.startLoading();
    }
}
