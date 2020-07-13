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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetMealArray_deprecated extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            ArrayList<String> data = new ArrayList<>();
            Connection mtemp = Jsoup.connect("https://m.search.naver.com/search.naver?sm=mtp_hty.top&where=m&query=%EC%84%9C%EC%9A%B8%EB%94%94%EC%A7%80%ED%85%8D%EA%B3%A0").method(Connection.Method.GET);
            Document meal = Jsoup.parse(new String(mtemp.execute().bodyAsBytes(), StandardCharsets.UTF_8));
            Elements e = meal.select("div.school_menu");
                Elements e2 = e.select("li.bx_list");
                for(Element e3 : e2){
                    //System.out.println(e3.getElementsByTag("strong").text() + "dff");
                    SimpleDateFormat f1 = new SimpleDateFormat("MM월 dd");
                    //일이 1자리수일때
                    SimpleDateFormat f2 = new SimpleDateFormat("d일");
                    SimpleDateFormat f2_two = new SimpleDateFormat("dd일");
                    //월이 2자리수일때
                    SimpleDateFormat f3 = new SimpleDateFormat("MM");
                    //월이 한자리수일때
                    SimpleDateFormat f4 = new SimpleDateFormat("M월");
                    Date time = new Date();
                    String t1 = f1.format(time);
                    String dateTmp = f2_two.format(time);
                    //System.out.println(f2.format(time));
                    //일이 2자리 수가 아니어서 0을 빼야할경우
                    if(Integer.parseInt(t1.substring(4, 6)) < 10) {
                        t1 = t1.substring(0, 4) + f2.format(time);
                        dateTmp = f2.format(time);
                    }
                    //월이 2자리수가 이니어서 0을 빼야할경우
                    if(Integer.parseInt(f3.format(time)) < 10) {
                        t1 = f4.format(time) + " " + dateTmp;
                    }
                    System.out.println(URLEncoder.encode(t1, "UTF-8"));
                    int t1next = Integer.parseInt(f1.format(time).substring(4, 6))+1;
                    //System.out.println(t1);
                    //System.out.println(t2);
                    System.out.println("HTML: "+URLEncoder.encode(e3.getElementsByTag("strong").text().substring(0, 6).trim(), "UTF-8"));
                    if(URLEncoder.encode(e3.getElementsByTag("strong").text().trim().substring(0, 5), "UTF-8").equals(URLEncoder.encode(t1, "UTF-8"))) {
                        //System.out.println("dkfdf");
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
