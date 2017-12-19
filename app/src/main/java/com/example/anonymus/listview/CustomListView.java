package com.example.anonymus.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class CustomListView extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Person> people;

    public CustomListView(@NonNull Context context, int resource, @NonNull ArrayList<Person> people) {
        super(context, resource, people);
        this.context = context;
        this.resource = resource;
        this.people = people;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.row_xml,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imView = convertView.findViewById(R.id.imView);
            viewHolder.tName = convertView.findViewById(R.id.tName);
            viewHolder.tAddres = convertView.findViewById(R.id.tAddress);
            viewHolder.tSex = convertView.findViewById(R.id.tSex);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = people.get(position);

        viewHolder.tName.setText(person.getName());
        viewHolder.tAddres.setText(person.getAddrress());
        viewHolder.tSex.setText(person.getSex());
        if (person.getSex() == "Male"){
            viewHolder.imView.setBackgroundResource(R.drawable.male);
        }else{
            viewHolder.imView.setBackgroundResource(R.drawable.female);
        }
        return convertView;
    }

    public class ViewHolder{
        ImageView imView;
        TextView tName,tAddres,tSex;
    }
}
