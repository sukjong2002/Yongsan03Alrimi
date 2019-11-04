package com.smaroid.y03noti.ui.up;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.smaroid.y03noti.MainActivity;
import com.smaroid.y03noti.R;
import com.smaroid.y03noti.parse.ArrayVO;
import com.smaroid.y03noti.parse.DownloadXml;
import com.smaroid.y03noti.parse.GetMealArray;
import com.smaroid.y03noti.parse.JsonToNeedArray;
import com.smaroid.y03noti.parse.ParseXml;
import com.smaroid.y03noti.ui.ListActivity;
import com.smaroid.y03noti.ui.listVO;
import com.smaroid.y03noti.ui.list_build;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.smaroid.y03noti.MainActivity.DOWN_ORD;
import static com.smaroid.y03noti.MainActivity.DOWN_STN_CODE;
import static com.smaroid.y03noti.MainActivity.LIST_DOWN;
import static com.smaroid.y03noti.MainActivity.LIST_UP;
import static com.smaroid.y03noti.MainActivity.UP_CODE;
import static com.smaroid.y03noti.MainActivity.UP_ORD;
import static com.smaroid.y03noti.MainActivity.UP_STN_CODE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = root.findViewById(R.id.upList);

        DownloadXml xml = new DownloadXml();
        GetMealArray m = new GetMealArray();
        String xmltmp = null;
        Object d = null;
        try {
            xmltmp = (String) xml.execute().get();
            //d = m.execute().get();

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ListActivity.class);
                intent.putExtra("stid", UP_STN_CODE[position]);
                intent.putExtra("ord", UP_ORD[position]);
                intent.putExtra("name", LIST_UP[position]);
                startActivity(intent);
            }
        });
        list_build adapter = new list_build(datalist, this.getContext(), container);
        listView.setAdapter(adapter);
        return root;
    }
}