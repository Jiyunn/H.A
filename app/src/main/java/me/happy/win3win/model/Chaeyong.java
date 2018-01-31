package me.happy.win3win.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by JY on 2017-05-24.
 */
@Data
public class Chaeyong {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;

    }

    static class Response {

        @SerializedName("header")
        @Expose
        private Header header;
        @SerializedName("body")
        @Expose
        private Body body;



    }

    static class Header {

        @SerializedName("resultCode")
        @Expose
        private String resultCode;
        @SerializedName("resultMsg")
        @Expose
        private String resultMsg;

           }

    static class Body {

        @SerializedName("items")
        @Expose
        private Gonggos gonggos;
        @SerializedName("numOfRows")
        @Expose
        private Long numOfRows;
        @SerializedName("pageNo")
        @Expose
        private Long pageNo;
        @SerializedName("totalCount")
        @Expose
        private Long totalCount;

    }
}

