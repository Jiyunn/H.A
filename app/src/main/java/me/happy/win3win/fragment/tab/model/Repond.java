package me.happy.win3win.fragment.tab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by JY on 2018-03-26.
 */

@Data
public class Repond {


    @SerializedName ("header")
    @Expose
    private Header header;

    @SerializedName ("body")
    @Expose
    private Body body;




}



