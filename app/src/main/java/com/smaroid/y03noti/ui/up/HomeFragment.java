package com.smaroid.y03noti.ui.up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.smaroid.y03noti.R;
import com.smaroid.y03noti.parse.ArrayVO;
import com.smaroid.y03noti.parse.DownloadXml;
import com.smaroid.y03noti.parse.JsonToNeedArray;
import com.smaroid.y03noti.parse.ParseXml;
import com.smaroid.y03noti.ui.listVO;
import com.smaroid.y03noti.ui.list_build;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.smaroid.y03noti.MainActivity.LIST_DOWN;
import static com.smaroid.y03noti.MainActivity.LIST_UP;
import static com.smaroid.y03noti.MainActivity.UP_CODE;
import static com.smaroid.y03noti.MainActivity.UP_ORD;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = root.findViewById(R.id.upList);

        DownloadXml xml = new DownloadXml();
        String xmltmp = null;
        try {
            xmltmp = (String) xml.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ParseXml parseXml = new ParseXml(xmltmp);
        JSONObject object = parseXml.getJson();
        JsonToNeedArray jtoarr = new JsonToNeedArray();
        ArrayList<ArrayVO> list = jtoarr.getArray(object);
        System.out.println(list.get(0).getPlateNo());

        ArrayList<listVO> datalist = new ArrayList<>();
        ArrayList<Integer> dataord = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            dataord.add(Integer.parseInt(list.get(i).getSectOrd()));
        }
        for (int i = 0; i < LIST_DOWN.length; i++) {
            datalist.add(new listVO(LIST_UP[i], UP_ORD[i], list, UP_CODE[i]));
        }

        list_build adapter = new list_build(datalist, this.getContext(), container);
        listView.setAdapter(adapter);
        return root;
    }
}