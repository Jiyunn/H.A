
package me.happy.win3win.fragment.tab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Result {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("createdDate")
    @Expose
    private CreatedDate createdDate;
    @SerializedName("grade")
    @Expose
    private String grade;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("lastModifiedDate")
    @Expose
    private LastModifiedDate lastModifiedDate;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("miscellaneous")
    @Expose
    private String miscellaneous;
    @SerializedName("objective")
    @Expose
    private String objective;
    @SerializedName("specialty")
    @Expose
    private String specialty;
    @SerializedName("userId")
    @Expose
    private Long userId;



}
