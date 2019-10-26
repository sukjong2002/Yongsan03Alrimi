package com.smaroid.y03noti.ui;

import com.smaroid.y03noti.parse.ArrayVO;

import java.util.ArrayList;

public class listVO {
    private String name;
    private String code;
    private int ord;
    private ArrayList<ArrayVO> dataord;

    public ArrayList<ArrayVO> getDataord() {
        return dataord;
    }

    public void setDataord(ArrayList<ArrayVO> dataord) {
        this.dataord = dataord;
    }

    public int getOrd() {
        return ord;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public listVO(String name, int ord, ArrayList<ArrayVO> dataord, String code) {
        this.name = name;
        this.ord = ord;
        this.dataord = dataord;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
