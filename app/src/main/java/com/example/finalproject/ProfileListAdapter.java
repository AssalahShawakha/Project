package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ProfileListAdapter extends BaseAdapter {
    private List<profileItem> profileItems;
    private Context context;

    public ProfileListAdapter(Context context, List<profileItem> profileItems) {
        this.context = context;
        this.profileItems = profileItems;
    }

    @Override
    public int getCount() {
        return profileItems.size();
    }

    @Override
    public profileItem getItem(int position) {
        return profileItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView labelTextView;
        TextView valueTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.labelTextView = convertView.findViewById(R.id.labelTextView);
            holder.valueTextView = convertView.findViewById(R.id.valueTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        profileItem profileItem = getItem(position);
        holder.labelTextView.setText(profileItem.getLabel());
        holder.valueTextView.setText(profileItem.getValue());

        return convertView;
    }
}
