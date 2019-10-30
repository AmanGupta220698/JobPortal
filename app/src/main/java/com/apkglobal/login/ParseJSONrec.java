package com.apkglobal.login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aman on 3/10/2018.
 */

public class ParseJSONrec {

    public static String[] company_names;
    public static String[] company_emails;
    public static String[] jobprofiles;
    public static String[] calls;
    public static String[] locations;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_COMPANY_NAME = "company_name";
    public static final String KEY_COMPANY_EMAIL = "company_email";

    public static final String KEY_JOBPROFILE = "jobprofile";

    public static final String KEY_LOCATION = "location";
    public static final String KEY_CALL = "company_call";
    private JSONArray users = null;

    private String json;

    public ParseJSONrec(String json){
        this.json = json;
    }

    protected void parseJSONrec(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            company_names = new String[users.length()];
            company_emails = new String[users.length()];

            jobprofiles = new String[users.length()];

            locations = new String[users.length()];
            calls = new String[users.length()];
            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                company_names[i] = jo.getString(KEY_COMPANY_NAME);
                company_emails[i] = jo.getString(KEY_COMPANY_EMAIL);

                jobprofiles[i] = jo.getString(KEY_JOBPROFILE);

                locations[i] = jo.getString(KEY_LOCATION);
                calls[i] = jo.getString(KEY_CALL);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


