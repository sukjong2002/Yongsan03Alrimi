package com.smaroid.y03noti.parse;

import org.json.JSONObject;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class ParseXml {
    String xml = null;
    XmlToJson xmlToJson = null;

    public ParseXml(String xml) {
        this.xml = xml;
    }

    public JSONObject getJson() {
        xmlToJson = new XmlToJson.Builder(xml).build();
        JSONObject jsonObject = xmlToJson.toJson();
        System.out.println(jsonObject.toString());
        return jsonObject;
    }
}
