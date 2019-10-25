package com.smaroid.y03noti;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smaroid.y03noti.parse.DownloadXml;
import com.smaroid.y03noti.parse.ParseXml;
import com.smaroid.y03noti.ui.list_build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static public final String[] LIST_DOWN = {"하얏트호텔", "필리핀대사관", "가야랑앞", "24시편의점, 디지텍고교", "성도약국", "시장", "해방촌", "녹사평역", "전쟁기념관"};
    static public final String[] LIST_UP = {"용산CJ나인파크", "용산e편한세상", "용산전자상가17동", "신용산, 지하차도", "신용산역", "KT용산지점", "삼각지역", "전쟁기념관", "녹사평역, 용산구청", "국군재정관리단"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_up, R.id.navigation_down)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

}
