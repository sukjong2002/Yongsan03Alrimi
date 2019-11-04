package com.smaroid.y03noti.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.smaroid.y03noti.R;
import com.smaroid.y03noti.parse.DownloadXml;
import com.smaroid.y03noti.parse.ParseXml;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.smaroid.y03noti.MainActivity.BIG_BUS_LIST;

public class ListActivity extends AppCompatActivity {
    TextView firstTime, firstSize, secondTime, secondSize, stnName;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        firstTime = findViewById(R.id.firstTime);
        firstSize = findViewById(R.id.firstSize);
        secondTime = findViewById(R.id.secondTime);
        secondSize = findViewById(R.id.secondSize);
        stnName = findViewById(R.id.stnName);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Intent intent = getIntent();
        String stid = intent.getExtras().getString("stid");
        String ord = String.valueOf(intent.getExtras().getInt("ord"));
        String name = intent.getExtras().getString("name");
        DownloadXml xml = new DownloadXml(stid, ord);
        String xmltmp = null;
        try {
            xmltmp = (String) xml.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ParseXml parseXml = new ParseXml(xmltmp);
        JSONObject object = parseXml.getJson();
        System.out.println(parseXml.getJson() + "dfk");
        try {
            JSONObject obj = object.getJSONObject("ServiceResult");
            JSONObject obj1 = obj.getJSONObject("msgBody");
            JSONObject jarray = obj1.getJSONObject("itemList");
            stnName.setText(name);
                firstTime.setText(jarray.optString("arrmsg1"));
                secondTime.setText(jarray.optString("arrmsg2"));
                if(!jarray.optString("arrmsg1").equals("출발대기")) {
                    for (int i = 0; i < BIG_BUS_LIST.length; i++) {
                        if(jarray.optString("plainNo1").equals(BIG_BUS_LIST[i])) {
                            firstSize.setText("큰버스");
                        }else{
                            firstSize.setText("작은버스");
                        }
                    }
                }else{
                    firstSize.setText("출발대기");
                }
                if(!jarray.optString("arrmsg2").equals("출발대기")) {
                    for (int i = 0; i < BIG_BUS_LIST.length; i++) {
                        if(jarray.optString("plainNo2").equals(BIG_BUS_LIST[i])) {
                            secondSize.setText("큰버스");
                        }else{
                            secondSize.setText("작은버스");
                        }
                    }
                }else{
            secondSize.setText("출발대기");
        }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
