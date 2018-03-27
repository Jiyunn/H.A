
package me.happy.win3win.fragment.tab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Resume {

    @SerializedName("code")
    @Expose
    private Long code;
    @SerializedName("result")
    @Expose
    private Result result;



}
