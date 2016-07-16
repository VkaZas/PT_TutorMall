package com.zju.app.tutormall.beans;

import org.json.JSONException;
import org.json.JSONObject;

public class CourseInfo {
    public String courseName;
    public String courseInfo;
    public String teacherName;
    public String credit;
    public int teacherID;
    public double amount;
    public String regTime;
    public String createTime;
    public int courseID;

    public CourseInfo(String cn, String ci, int cid, String tn, String c, int tid, double a, String rt) {
        courseName = cn;
        courseInfo = ci;
        teacherName = tn;
        credit = c;
        teacherID = tid;
        amount = a;
        regTime = rt;
        courseID = cid;
    }

    public CourseInfo(JSONObject jObj) throws JSONException {
        courseName = jObj.getString("course_name");
        teacherID = jObj.getInt("user_id");
        teacherName = jObj.getString("user_name");
        courseInfo = jObj.getString("course_info");
        amount = jObj.getDouble("amount");
        credit = jObj.getString("jwb_credit");
        createTime = jObj.getString("create_time");
        courseID = jObj.getInt("course_id");
    }
}
