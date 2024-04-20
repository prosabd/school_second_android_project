package com.example.androidprojecttp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    private int selectedItem = -1; // no item selected by default

    public CustomAdapter(Context context, List<String> items) {
        super(context, R.layout.list_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.textview_viewbook_booklist);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox_viewbook_booklist);

        textView.setText(getItem(position));

        if (position == selectedItem) {
            convertView.setBackgroundColor(Color.GRAY); // change background color
            checkBox.setChecked(true);
        } else {
            convertView.setBackgroundColor(Color.TRANSPARENT); // default color
            checkBox.setChecked(false);
        }

        return convertView;
    }

    public void setSelectedItem(int position) {
        selectedItem = position; // update selection
        notifyDataSetChanged(); // refresh ListView
    }
}
