package com.smaroid.y03noti.parse;

import org.json.JSONObject;

public class CheckCode {
    String code = null;

    public CheckCode() {
    }

    public String getCode() {
        DownloadXml xml = new DownloadXml();
        String xmltmp = null;
        try {
            xmltmp = (String) xml.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ParseXml parseXml = new ParseXml(xmltmp);
        JSONObject object = parseXml.getJson();
        try {
            JSONObject obj = object.getJSONObject("ServiceResult");
            JSONObject obj1 = obj.getJSONObject("msgHeader");
            code = obj1.optString("headerCd");
        }catch (Exception e) {
            e.printStackTrace();
        }

        return code;
    }
}
