package com.smaroid.y03noti.ui.down;

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

import static com.smaroid.y03noti.MainActivity.DOWN_CODE;
import static com.smaroid.y03noti.MainActivity.DOWN_ORD;
import static com.smaroid.y03noti.MainActivity.LIST_DOWN;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ListView listView = root.findViewById(R.id.downList);

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
            datalist.add(new listVO(LIST_DOWN[i], DOWN_ORD[i], list, DOWN_CODE[i]));
        }



        list_build adapter = new list_build(datalist, this.getContext(), container);
        listView.setAdapter(adapter);
        return root;
    }
}