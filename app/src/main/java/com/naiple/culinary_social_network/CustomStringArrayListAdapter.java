package com.naiple.culinary_social_network;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomStringArrayListAdapter extends ArrayAdapter<String> {
    private LayoutInflater inflater;
    private int layout;

    private String[] names;

    public CustomStringArrayListAdapter(@NonNull Context context, int resource, String[] names) {
        super(context, resource, names);
        this.layout = resource;
        this.names = names;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);
        TextView textView = view.findViewById(R.id.listTextValue);
        textView.setText(this.names[position]);
        return view;
    }
}

