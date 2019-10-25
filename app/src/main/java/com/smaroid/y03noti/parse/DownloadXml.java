package com.smaroid.y03noti.parse;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DownloadXml extends AsyncTask {
    private String key = "boirdXwYHVTjsMWaSDyBR9Cw0I4nQNYMDcNpVNRQJXYScydtWG5PJrNbMbch8hzbUkltGHDtTic8P4ow7bI64Q%3D%3D";
    //    private String routeId = "102900004";
    private String routeId = "100100104";       //702A
    private String urlString = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?serviceKey=" + key + "&busRouteId=" + routeId;

    @Override
    protected Object doInBackground(Object[] objects) {
        String xml = "";
        try {
            StringBuilder urlBuilder = new StringBuilder("http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + key); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + URLEncoder.encode(routeId, "UTF-8"));
            System.out.println(urlBuilder.toString());
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());
            xml = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
