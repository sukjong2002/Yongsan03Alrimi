package com.smaroid.y03noti.parse;

import android.os.AsyncTask;

import com.smaroid.y03noti.ui.meal.MealVO;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GetMealArray extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        ArrayList<MealVO> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("YYYYMMdd");
        Date time = new Date();
        Date dayplus = new Date(time.getTime() + TimeUnit.DAYS.toMillis(5));
        String today = format.format(time);
        String todayplus = format.format(dayplus);
        try {
            String neis = "https://open.neis.go.kr/hub/mealServiceDietInfo?Type=json&KEY=ecabe857ea114a09a0db1163ae5fa947&ATPT_OFCDC_SC_CODE=B10&SD_SCHUL_CODE=7010572&MLSV_FROM_YMD="+today+"&MLSV_TO_YMD="+todayplus;
            System.out.println(neis);
            URL url = new URL(neis);
            URLConnection conn = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer sb =
                    new StringBuffer();
            // read contents line by line and store in the string
            while ((line =
                    br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            JSONObject json = new JSONObject(sb.toString());
            JSONArray ifo = json.getJSONArray("mealServiceDietInfo");
            JSONObject ifoobj = ifo.getJSONObject(1);
            JSONArray row = ifoobj.getJSONArray("row");
            for (int i = 0; i<row.length(); i++) {
                MealVO vo = new MealVO();
                JSONObject obj = row.getJSONObject(i);
                String meal = obj.getString("DDISH_NM");
                meal = meal.replace("<br/>", "\n");
                vo.setMeal(meal);
                String dateStr = obj.getString("MLSV_YMD");
                DateFormat stf = new SimpleDateFormat("yyyyMMdd");
                Date mealDate = stf.parse(dateStr);
                vo.setDate(mealDate);
                String cal = obj.getString("CAL_INFO");
                vo.setCal(cal);
                list.add(vo);
            }
            return list;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
