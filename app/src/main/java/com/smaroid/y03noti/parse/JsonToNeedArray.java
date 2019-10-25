package com.smaroid.y03noti.parse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonToNeedArray {

    public JsonToNeedArray() {
    }

    public ArrayList<ArrayVO> getArray(JSONObject object) {
        ArrayList<ArrayVO> array = new ArrayList<>();
        try{
            JSONObject obj = object.getJSONObject("ServiceResult");
            JSONObject obj1 = obj.getJSONObject("msgBody");
            JSONArray jarray = obj1.getJSONArray("itemList");
            System.out.println(jarray.length());
            for (int i = 0; i < jarray.length(); i++) {
                HashMap map = new HashMap<>();
                JSONObject jObject = jarray.getJSONObject(i);
                ArrayVO arrayVO = new ArrayVO();
                arrayVO.setLastStnId(jObject.optString("lastStnId"));
                arrayVO.setPlateNo(jObject.optString("plainNo"));
                arrayVO.setStopflag(jObject.optString("stopFlag"));
                arrayVO.setSectOrd(jObject.optString("sectOrd"));
                array.add(arrayVO);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }


}
