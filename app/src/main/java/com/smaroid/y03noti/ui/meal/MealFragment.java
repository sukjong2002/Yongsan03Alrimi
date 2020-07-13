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
        TextView todayCal = root.findViewById(R.id.todayCal);
        TextView nextDate = root.findViewById(R.id.nextDate);
        TextView nextMeal = root.findViewById(R.id.nextMeal);
        TextView nextCal = root.findViewById(R.id.nextCal);

        GetMealArray test = new GetMealArray();
        ArrayList<MealVO> todaylunch = null;
        try {
            todaylunch = (ArrayList<MealVO>) test.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat f1 = new SimpleDateFormat("MM월 dd일");
        if(todaylunch == null) {
            todayMeal.setText("오류 발생");
        }
        if(todaylunch.size() >= 2) {
            todayDate.setText(f1.format(todaylunch.get(0).getDate()));
            todayMeal.setText(todaylunch.get(0).getMeal());
            todayCal.setText(todaylunch.get(0).getCal());
            nextDate.setText(f1.format(todaylunch.get(1).getDate()));
            nextMeal.setText(todaylunch.get(1).getMeal());
            nextCal.setText(todaylunch.get(1).getCal());
        }else if(todaylunch.size() == 1) {
            todayDate.setText(f1.format(todaylunch.get(0).getDate()));
            todayMeal.setText(todaylunch.get(0).getMeal());
        }else{

        }
        return root;
    }
}