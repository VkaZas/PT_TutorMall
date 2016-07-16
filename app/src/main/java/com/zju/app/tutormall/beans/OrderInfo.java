package com.zju.app.tutormall.beans;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderInfo {
    public String orderInfo;
    public int orderState;
    public int orderID;
    public String courseName;
    public int courseID;
    public String courseInfo;
    public String stuInfo;
    public String stuName;
    public int stuID;
    public String teacherName;
    public int teacherID;
    public String createTime;
    public double amount;

    public OrderInfo(String oi, String os, int oid, String cn, String ci, int cid, String si, String sn, int sid, int ost) {
        orderInfo = oi;
        orderState = ost;
        orderID = oid;
        courseName = cn;
        courseInfo = ci;
        courseID = cid;
        stuName = sn;
        stuInfo = si;
        stuID = sid;
    }

    public OrderInfo(JSONObject jObj) throws JSONException {
        if (jObj.has("order_info")) orderInfo = jObj.getString("order_info"); else orderInfo = "";
        if (jObj.has("order_state")) orderState = jObj.getInt("order_state"); else orderState = 0;
        if (jObj.has("order_id")) orderID = jObj.getInt("order_id"); else orderID = 0;
        if (jObj.has("course_name")) courseName = jObj.getString("course_name"); else courseName = "";
        if (jObj.has("course_info")) courseInfo = jObj.getString("course_info"); else courseInfo = "";
        if (jObj.has("course_id")) courseID = jObj.getInt("course_id"); else courseID = 0;
        if (jObj.has("student_name")) stuName = jObj.getString("student_name"); else stuName="";
        if (jObj.has("student_id")) stuID = jObj.getInt("student_id"); else stuID = 0;
        if (jObj.has("student_info")) stuInfo = jObj.getString("student_info"); else stuInfo = "";
        if (jObj.has("teacher_id")) teacherID = jObj.getInt("teacher_id"); else teacherID = 0;
        if (jObj.has("teacher_name")) teacherName = jObj.getString("teacher_name"); else teacherName = "";
        if (jObj.has("create_time")) createTime = jObj.getString("create_time"); else createTime = "";
        if (jObj.has("amount")) amount = jObj.getDouble("amount"); else amount = 1000;
    }
}
