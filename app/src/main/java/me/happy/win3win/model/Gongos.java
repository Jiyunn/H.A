package me.happy.win3win.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by JY on 2017-05-25.
 */

public class Items {

    @SerializedName("item")
    @Expose
    private ArrayList<Item> itemList = null;

    public ArrayList<Item> getItemList() {
        return itemList;
    }


}
