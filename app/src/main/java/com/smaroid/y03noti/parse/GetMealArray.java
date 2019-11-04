package com.smaroid.y03noti.parse;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class GetMealArray extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            ArrayList<String> data = new ArrayList<>();
            Document meal = Jsoup.connect("https://m.search.naver.com/search.naver?sm=mtp_hty.top&where=m&query=%EC%84%9C%EC%9A%B8%EB%94%94%EC%A7%80%ED%85%8D%EA%B3%A0").get();
            Elements e = meal.select("div.school_menu");
            for (Element e1 : e
                    ) {
                Elements n = e1.getElementsByTag("strong");
                System.out.println(n.get(14).ownText());
                }



        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
