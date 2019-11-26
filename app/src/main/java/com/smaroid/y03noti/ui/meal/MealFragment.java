package com.smaroid.y03noti.ui.meal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.smaroid.y03noti.R;
import com.smaroid.y03noti.parse.ArrayVO;
import com.smaroid.y03noti.parse.DownloadXml;
import com.smaroid.y03noti.parse.GetMealArray;
import com.smaroid.y03noti.parse.JsonToNeedArray;
import com.smaroid.y03noti.parse.ParseXml;
import com.smaroid.y03noti.ui.ListActivity;
import com.smaroid.y03noti.ui.down.DashboardViewModel;
import com.smaroid.y03noti.ui.listVO;
import com.smaroid.y03noti.ui.list_build;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.smaroid.y03noti.MainActivity.DOWN_CODE;
import static com.smaroid.y03noti.MainActivity.DOWN_ORD;
import static com.smaroid.y03noti.MainActivity.DOWN_STN_CODE;
import static com.smaroid.y03noti.MainActivity.LIST_DOWN;

public class MealFragment extends Fragment {

    private MealViewModel mealViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        mealViewModel =
                ViewModelProviders.of(this).get(MealViewModel.class);
        View root = inflater.inflate(R.layout.lunch_fragment, container, false);
        TextView todayMeal = root.findViewById(R.id.todayMeal);
        TextView todayDate = root.findViewById(R.id.todayDate);

        GetMealArray test = new GetMealArray();
        String todaylunch = null;
        try {
            todaylunch = (String) test.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat f1 = new SimpleDateFormat("MM월 dd일");
        Date time = new Date();
        String t1 = f1.format(time);
        todayDate.setText(t1);
        todayMeal.setText(todaylunch);
        return root;
    }
}