package com.example.gitbug.App;

import android.app.Application;
import com.example.gitbug.NetworkHandler.Network;


public class appController extends Application {
    private static appController instance;
    public static synchronized appController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
