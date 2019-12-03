package com.smaroid.y03noti.parse;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.smaroid.y03noti.MainActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetMealArray extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            ArrayList<String> data = new ArrayList<>();
            Document meal = Jsoup.connect("https://m.search.naver.com/search.naver?sm=mtp_hty.top&where=m&query=%EC%84%9C%EC%9A%B8%EB%94%94%EC%A7%80%ED%85%8D%EA%B3%A0").get();
            Elements e = meal.select("div.school_menu");
                Elements e2 = e.select("li.bx_list");
                for(Element e3 : e2){
                    //System.out.println(e3.getElementsByTag("strong").text() + "dff");
                    SimpleDateFormat f1 = new SimpleDateFormat("MM월 dd");
                    SimpleDateFormat f2 = new SimpleDateFormat("dd");
                    Date time = new Date();
                    String t1 = f1.format(time);
                    if(Integer.parseInt(f2.format(time)) < 10) {
                        t1 = t1.substring(0, 4) + Integer.parseInt(f2.format(time));
                        System.out.println(t1);
                    }
                    int t1next = Integer.parseInt(f1.format(time).substring(4, 6))+1;
                    //System.out.println(t1);
                    //System.out.println(t2);
                    //System.out.println(e3.getElementsByTag("strong").text().substring(0, 6));
                    if(e3.getElementsByTag("strong").text().substring(0, 6).equals(t1) || e3.getElementsByTag("strong").text().substring(0, 5).equals(t1)) {
                        System.out.println("dkfdf");
                        Elements e4 = e3.select("ul");
                        String tmp = "";
                        for (Element e5: e4) {
                            tmp+=e5.getElementsByTag("li").text();
                            tmp+=System.lineSeparator();
                        }
                        System.out.println(e3.getElementsByTag("strong").text().substring(0, 6));
                        System.out.println(t1next);
                    //}else if(e3.getElementsByTag("strong").text().substring(0, 6).equals(t2)) {
                        System.out.println(tmp);
                        System.out.println("ddff");
                        return tmp;
                    }
                }



        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
