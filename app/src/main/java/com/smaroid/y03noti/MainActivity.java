package com.smaroid.y03noti;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    static public final String[] LIST_DOWN = {"하얏트호텔", "필리핀대사관", "가야랑앞", "24시편의점, 디지텍고교", "성도약국", "시장", "해방촌", "녹사평역", "전쟁기념관"};
    static public final int[] DOWN_ORD = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    static public final String[] DOWN_CODE = {"03500", "03753", "03505", "03509", "03512", "03516", "03151", "03524", "03184"};
    static public final String[] LIST_UP = {"용산CJ나인파크", "용산e편한세상", "용산전자상가17동", "신용산, 지하차도", "신용산역", "KT용산지점", "삼각지역", "전쟁기념관", "녹사평역, 용산구청", "국군재정관리단"};
    static public final int[] UP_ORD = {16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    static public final String[] UP_CODE = {"03743", "03744", "03132", "03252", "03561", "03564", "03567", "03185", "03738", "03152"};
    static public final String[] BIG_BUS_LIST = {"서울75사5718", "서울75사5720"};

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getConStat(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_up, R.id.navigation_down)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void getConStat(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork == null) {
            Toast.makeText(context, "인터넷 연결을 확인해 주세요.", Toast.LENGTH_SHORT).show();
            finishAndRemoveTask();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu1:
                Intent intent = new Intent(this, DisplayInfoActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
