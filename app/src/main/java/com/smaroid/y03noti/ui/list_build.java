package com.smaroid.y03noti.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.smaroid.y03noti.R;

import java.util.ArrayList;

public class list_build extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<listVO> list = null;
    ViewGroup container = null;

    public list_build(ArrayList<listVO> data, Context mContext, ViewGroup container) {
        list = data;
        this.mContext = mContext;
        this.container = container;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            //final Context context = parent.getContext();
            if(mLayoutInflater  == null) {
                mLayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = mLayoutInflater.inflate(R.layout.layout_list, container, false);

        TextView stnName = convertView.findViewById(R.id.StnName);

        stnName.setText(list.get(position).getName());
        return convertView;
    }
}
