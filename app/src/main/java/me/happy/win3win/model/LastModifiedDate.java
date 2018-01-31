
package me.happy.win3win.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class LastModifiedDate {

    @SerializedName("afterNow")
    @Expose
    private Boolean afterNow;
    @SerializedName("beforeNow")
    @Expose
    private Boolean beforeNow;
    @SerializedName("equalNow")
    @Expose
    private Boolean equalNow;


}
