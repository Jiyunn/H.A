
package kr.happy.myarmy.Server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Items {

    @SerializedName("item")
    @Expose
    private ArrayList<Item> itemList= null;


    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
}
