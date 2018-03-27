package me.happy.win3win.fragment.tab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by JY on 2018-03-26.
 */

@Data
class Header {

    @SerializedName ("resultCode")
    @Expose
    private String resultCode;
    @SerializedName ("resultMsg")
    @Expose
    private String resultMsg;

}
