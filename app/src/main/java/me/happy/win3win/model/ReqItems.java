package me.happy.win3win.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by JY on 2017-05-16.
 */

public class ReqItems {

    @SerializedName("code")
    private long code;


    @SerializedName("result")
    private ArrayList<Gonggo> requestList;

    public long getCode() {
        return code;
    }


    public ArrayList<Gonggo> getRequestList() {
        return requestList;
    }
}
