package com.example.koira.firebasefinalreview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by koira on 12/9/2017.
 */

public class PersonAdapter extends ArrayAdapter<Person>{

    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Person person = getItem(position);
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.textViewName = convertView.findViewById(R.id.textViewName);
            viewHolder.textViewEmail = convertView.findViewById(R.id.textViewEmail);
            viewHolder.textViewPhone = convertView.findViewById(R.id.textViewPhone);
            viewHolder.textViewDept = convertView.findViewById(R.id.textViewDept);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //set the data from the person object
        viewHolder.textViewName.setText(person.getName());
        viewHolder.textViewEmail.setText(person.getEmail());
        viewHolder.textViewPhone.setText(person.getPhone());
        viewHolder.textViewDept.setText(person.getDept());
        viewHolder.imageView.setImageResource(person.picID);

        return convertView;
    }

    private static class ViewHolder{
        TextView textViewName;
        TextView textViewEmail;
        TextView textViewPhone;
        TextView textViewDept;
        ImageView imageView;
    }
}
