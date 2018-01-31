
package me.happy.win3win.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//이력서가져오는 클래스
public class MyResume {

    @SerializedName("code")
    @Expose
    private Long code;
    @SerializedName("result")
    @Expose
    private Result result;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
