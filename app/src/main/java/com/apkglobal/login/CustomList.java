package com.apkglobal.login;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Aman on 3/10/2018.
 */

public class CustomList extends ArrayAdapter<String> {
    private String[] dobs;
    private String[] names;
    private String[] emails;
    private String[] qualifications;
    private String[] phones;
    private Activity context;

    public CustomList(Activity context, String[] dobs, String[] names, String[] emails, String [] qualifications,String [] phones) {
        super(context, R.layout.datalist, names);
        this.context = context;
        this.dobs = dobs;
        this.names = names;
        this.emails = emails;
        this.qualifications=qualifications;
        this.phones=phones;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.datalist, null, true);
        TextView textViewDob = (TextView) listViewItem.findViewById(R.id.tv_dob);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.tv_name);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.tv_email);
        TextView textViewQualification = (TextView) listViewItem.findViewById(R.id.tv_qualification);
        TextView textViewPhone = (TextView) listViewItem.findViewById(R.id.tv_phone);

        textViewDob.setText(dobs[position]);
        textViewName.setText(names[position]);
        textViewEmail.setText(emails[position]);
        textViewQualification.setText(qualifications[position]);
        textViewQualification.setText(phones[position]);
        return listViewItem;
    }
}


