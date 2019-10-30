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

public class Customlistrec extends ArrayAdapter<String> {
    private String[] company_names;
    private String[] company_emails;


    private String[] jobprofiles;
    private String[] locations;
    private Activity context;
    private String[] calls;

    public Customlistrec(Activity context, String[] comapany_names, String[] comapany_emails,  String [] jobprofiles, String [] locations,String[] calls) {
        super(context, R.layout.datalistrec, comapany_names);
        this.context = context;
        this.company_names = comapany_names;
        this.company_emails = comapany_emails;


        this.jobprofiles = jobprofiles;
        this.locations=locations;
        this.calls=calls;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.datalistrec, null, true);
        TextView textViewCompany_name = (TextView) listViewItem.findViewById(R.id.tv_comapny_name);
        TextView textViewComapany_email = (TextView) listViewItem.findViewById(R.id.tv_company_email);

        TextView textViewJobprofile = (TextView) listViewItem.findViewById(R.id.tv_jobprofile);
        TextView textViewLocation = (TextView) listViewItem.findViewById(R.id.tv_location);
        TextView textViewSalary = (TextView) listViewItem.findViewById(R.id.company_call);

        textViewCompany_name.setText(company_names[position]);
        textViewComapany_email.setText(company_emails[position]);


        textViewJobprofile.setText(jobprofiles[position]);
        textViewLocation.setText(locations[position]);
        textViewSalary.setText(calls[position]);
        return listViewItem;
    }
}


