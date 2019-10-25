package com.smaroid.y03noti.ui.up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.smaroid.y03noti.R;
import com.smaroid.y03noti.ui.down.DashboardViewModel;
import com.smaroid.y03noti.ui.listVO;
import com.smaroid.y03noti.ui.list_build;

import java.util.ArrayList;

import static com.smaroid.y03noti.MainActivity.LIST_DOWN;
import static com.smaroid.y03noti.MainActivity.LIST_UP;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ListView listView = root.findViewById(R.id.upList);

        ArrayList<listVO> datalist = new ArrayList<>();
        for (int i = 0; i < LIST_UP.length; i++) {
            datalist.add(new listVO(LIST_UP[i]));
        }

        list_build adapter = new list_build(datalist, this.getContext(), container);
        listView.setAdapter(adapter);
        return root;
    }
}