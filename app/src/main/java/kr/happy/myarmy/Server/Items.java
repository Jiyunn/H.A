
package kr.happy.myarmy.Server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Items {

    @SerializedName("item")
    @Expose
    private ArrayList<Item> itemList= null;

    @SerializedName("code")
    private long code;

    @SerializedName("result")
    @Expose
    private ArrayList<ArrayList<Item>> resultList= null;

    public Items(ArrayList<ArrayList<Item>> resultList) {
        this.resultList = resultList;
    }


    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public ArrayList<ArrayList<Item>> getResultList() {
        return resultList;
    }
}
