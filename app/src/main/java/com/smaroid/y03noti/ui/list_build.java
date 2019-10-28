package com.smaroid.y03noti.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smaroid.y03noti.R;
import com.smaroid.y03noti.parse.ArrayVO;

import java.util.ArrayList;

import static com.smaroid.y03noti.MainActivity.BIG_BUS_LIST;

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
        ImageView lineImg = convertView.findViewById(R.id.lineImg);
       TextView plate = convertView.findViewById(R.id.plate);
       TextView type = convertView.findViewById(R.id.type);
       TextView stnCode = convertView.findViewById(R.id.StnCode);
        for (int i = 0; i < list.get(position).getDataord().size(); i++) {
            ArrayList<ArrayVO> dataord = list.get(position).getDataord();
            if(Integer.parseInt(dataord.get(i).getSectOrd()) == list.get(position).getOrd()) {
                lineImg.setImageResource(R.mipmap.ic_bus_located1);
                plate.setVisibility(View.VISIBLE);
                type.setVisibility(View.VISIBLE);
                plate.setText(dataord.get(i).getPlateNo().substring(2));
                for (int j = 0; j < BIG_BUS_LIST.length; j++) {
                    if(dataord.get(i).getPlateNo().trim().equals(BIG_BUS_LIST[j]))
                        type.setText("큰버스");
                }
            }
        }

        stnCode.setText(list.get(position).getCode());
        stnName.setText(list.get(position).getName());
        return convertView;
    }
}
