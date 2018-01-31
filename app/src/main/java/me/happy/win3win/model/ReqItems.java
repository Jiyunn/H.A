package me.happy.win3win.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Data;

/**
 * Created by JY on 2017-05-16.
 */

@Data
public class ReqItems {

    @SerializedName("code")
    private long code;

    @SerializedName("result")
    private ArrayList<Gonggo> requestList;

}
