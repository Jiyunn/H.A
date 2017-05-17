package kr.happy.myarmy.Server;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by JY on 2017-05-16.
 */

public class ReqItems {

    @SerializedName("code")
    private long code;


    @SerializedName("result")
    private ArrayList<Item> requestList;

    public long getCode() {
        return code;
    }


    public ArrayList<Item> getRequestList() {
        return requestList;
    }
}
