
package me.happy.win3win.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CreatedDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(CreatedDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LastModifiedDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LastModifiedDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(String miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
