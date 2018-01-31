package me.happy.win3win.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import me.happy.win3win.network.Items;

/**
 * Created by JY on 2017-05-24.
 */

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

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }

        public Body getBody() {
            return body;
        }

        public void setBody(Body body) {
            this.body = body;
        }

    }

    static class Header {

        @SerializedName("resultCode")
        @Expose
        private String resultCode;
        @SerializedName("resultMsg")
        @Expose
        private String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }

    static class Body {

        @SerializedName("items")
        @Expose
        private Items items;
        @SerializedName("numOfRows")
        @Expose
        private Long numOfRows;
        @SerializedName("pageNo")
        @Expose
        private Long pageNo;
        @SerializedName("totalCount")
        @Expose
        private Long totalCount;

        public Items getItems() {
            return items;
        }


        public Long getNumOfRows() {
            return numOfRows;
        }



        public Long getPageNo() {
            return pageNo;
        }


        public Long getTotalCount() {
            return totalCount;
        }


    }
}

