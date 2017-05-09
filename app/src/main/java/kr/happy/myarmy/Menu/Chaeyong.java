
package kr.happy.myarmy.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kr.happy.myarmy.Retrofit2.Items;

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

        class Body {

            @SerializedName("items")
            @Expose
            private Items items;
            @SerializedName("numOfRows")
            @Expose
            private int numOfRows;
            @SerializedName("pageNo")
            @Expose
            private int pageNo;
            @SerializedName("totalCount")
            @Expose
            private int totalCount;

            public Items getItems() {
                return items;
            }

            public void setItems(Items items) {
                this.items = items;
            }

            public int getNumOfRows() {
                return numOfRows;
            }

            public void setNumOfRows(int numOfRows) {
                this.numOfRows = numOfRows;
            }

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

        }


    }

}
