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

public class Customlistbpo extends ArrayAdapter<String> {
    private String[] company_names;
    private String[] company_emails;
    private String[] experiences;
    private String[] salarys;
    private String[] jobprofiles;
    private String[] locations;
    private Activity context;

    public Customlistbpo(Activity context, String[] comapany_names, String[] comapany_emails, String[] experiences, String [] salarys, String [] jobprofiles, String [] locations) {
        super(context, R.layout.datalistjob, comapany_names);
        this.context = context;
        this.company_names = comapany_names;
        this.company_emails = comapany_emails;
        this.experiences = experiences;
        this.salarys=salarys;
        this.jobprofiles = jobprofiles;
        this.locations=locations;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.datalistbpo, null, true);
        TextView textViewCompany_name = (TextView) listViewItem.findViewById(R.id.tv_comapny_name);
        TextView textViewComapany_email = (TextView) listViewItem.findViewById(R.id.tv_company_email);
        TextView textViewExperience = (TextView) listViewItem.findViewById(R.id.tv_experience);
        TextView textViewSalary = (TextView) listViewItem.findViewById(R.id.tv_salary);
        TextView textViewJobprofile = (TextView) listViewItem.findViewById(R.id.tv_jobprofile);
        TextView textViewLocation = (TextView) listViewItem.findViewById(R.id.tv_location);

        textViewCompany_name.setText(company_names[position]);
        textViewComapany_email.setText(company_emails[position]);
        textViewExperience.setText(experiences[position]);
        textViewSalary.setText(salarys[position]);
        textViewJobprofile.setText(jobprofiles[position]);
        textViewLocation.setText(locations[position]);
        return listViewItem;
    }
}


