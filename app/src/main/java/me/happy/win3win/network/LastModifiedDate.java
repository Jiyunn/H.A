
package me.happy.win3win.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public Boolean getAfterNow() {
        return afterNow;
    }

    public void setAfterNow(Boolean afterNow) {
        this.afterNow = afterNow;
    }

    public Boolean getBeforeNow() {
        return beforeNow;
    }

    public void setBeforeNow(Boolean beforeNow) {
        this.beforeNow = beforeNow;
    }

    public Boolean getEqualNow() {
        return equalNow;
    }

    public void setEqualNow(Boolean equalNow) {
        this.equalNow = equalNow;
    }

}
