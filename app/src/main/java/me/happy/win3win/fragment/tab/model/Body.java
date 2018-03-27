package me.happy.win3win.fragment.tab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by JY on 2018-03-26.
 */

@Data
public class Body {

    @SerializedName ("items")
    @Expose
    private Gonggos gonggos;
    @SerializedName ("numOfRows")
    @Expose
    private Long numOfRows;
    @SerializedName ("pageNo")
    @Expose
    private Long pageNo;
    @SerializedName ("totalCount")
    @Expose
    private Long totalCount;

}