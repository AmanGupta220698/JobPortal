package com.apkglobal.login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aman on 3/10/2018.
 */

public class ParseJSONjob {

    public static String[] company_names;
    public static String[] company_emails;
    public static String[] experiences;
    public static String[] jobprofiles;
    public static String[] salarys;
    public static String[] locations;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_COMPANY_NAME = "company_name";
    public static final String KEY_COMPANY_EMAIL = "company_email";
    public static final String KEY_EXPERIENCE = "experience";
    public static final String KEY_JOBPROFILE = "jobprofile";
    public static final String KEY_SALARY = "salary";
    public static final String KEY_LOCATION = "location";
    private JSONArray users = null;

    private String json;

    public ParseJSONjob(String json){
        this.json = json;
    }

    protected void parseJSONjob(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            company_names = new String[users.length()];
            company_emails = new String[users.length()];
            experiences = new String[users.length()];
            jobprofiles = new String[users.length()];
            salarys = new String[users.length()];
            locations = new String[users.length()];
            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                company_names[i] = jo.getString(KEY_COMPANY_NAME);
                company_emails[i] = jo.getString(KEY_COMPANY_EMAIL);
                experiences[i] = jo.getString(KEY_EXPERIENCE);
                jobprofiles[i] = jo.getString(KEY_JOBPROFILE);
                salarys[i] = jo.getString(KEY_SALARY);
                locations[i] = jo.getString(KEY_LOCATION);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


