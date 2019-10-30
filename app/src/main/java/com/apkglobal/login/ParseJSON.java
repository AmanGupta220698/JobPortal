package com.apkglobal.login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aman on 3/10/2018.
 */

public class ParseJSON {

    public static String[] qualifications;
    public static String[] names;
    public static String[] emails;
    public static String[] dobs;
    public static String[] phones;
    public static final String JSON_ARRAY = "result";
    public static final String KEY_QUALIFICATION = "qualification";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_DOB = "dob";
    public static final String KEY_PHONE = "phone";
    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    public void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            dobs = new String[users.length()];
            names = new String[users.length()];
            emails = new String[users.length()];
            qualifications = new String[users.length()];
            phones = new String[users.length()];
            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                dobs[i] = jo.getString(KEY_DOB);
                names[i] = jo.getString(KEY_NAME);
                emails[i] = jo.getString(KEY_EMAIL);
                qualifications[i] = jo.getString(KEY_QUALIFICATION);
                phones[i] = jo.getString(KEY_PHONE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


