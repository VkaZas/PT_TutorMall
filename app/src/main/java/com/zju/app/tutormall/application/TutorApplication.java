package com.zju.app.tutormall.application;

import android.app.Application;

public class TutorApplication extends Application{

    private String ServerAddress;
    private String AccessToken;

    @Override
    public void onCreate() {
        super.onCreate();
        setServerAddress("http://10.180.92.241:7777/");
    }

    public void setServerAddress(String serverAddress) {
        ServerAddress = serverAddress;
    }

    public String getServerAddress() {
        return ServerAddress;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public void setAccessToken(String accessToken) {
        AccessToken = accessToken;
    }
}
