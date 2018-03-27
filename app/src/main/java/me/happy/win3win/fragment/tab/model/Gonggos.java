package me.happy.win3win.fragment.tab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Data;

/**
 * Created by JY on 2017-05-25.
 */
@Data
public class Gonggos {

    @SerializedName("item")
    @Expose
    private ArrayList<Gonggo> gonggoList = null;

}
