package com.zju.app.tutormall.application;

import android.app.Application;
import com.zju.app.tutormall.beans.CourseInfo;
import com.zju.app.tutormall.beans.OrderInfo;

import java.util.ArrayList;

public class TutorApplication extends Application{

    private String ServerAddress;
    private String AccessToken;
    private String email;
    private String userName;

    public boolean update;

    public ArrayList<CourseInfo> teachCourseInfoList;
    public ArrayList<CourseInfo> propCourseInfoList;
    public ArrayList<CourseInfo> srchCourseInfoList;
    public ArrayList<OrderInfo> stuOrderList;

    @Override
    public void onCreate() {
        super.onCreate();
        setServerAddress("http://10.180.81.33:7777/");
        teachCourseInfoList = new ArrayList<>();
        propCourseInfoList = new ArrayList<>();
        srchCourseInfoList = new ArrayList<>();
        stuOrderList = new ArrayList<>();
        AccessToken = "";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
