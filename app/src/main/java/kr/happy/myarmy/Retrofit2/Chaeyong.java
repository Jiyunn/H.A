package kr.happy.myarmy.Retrofit2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by JY on 2017-04-26.
 */

public class Chaeyong {

    @SerializedName("items")
    private ArrayList<Gongo> cyJeongBoList=null;


    public ArrayList<Gongo> getCyJeongBoList() {
        return cyJeongBoList;
    }

    public void setCyJeongBoList(ArrayList<Gongo> cyJeongBoList) {

        this.cyJeongBoList = cyJeongBoList;
    }
}
