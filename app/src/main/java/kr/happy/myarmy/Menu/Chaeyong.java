
package kr.happy.myarmy.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kr.happy.myarmy.Server.Items;

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



        public Body getBody() {
            return body;
        }


        static class Header {

            @SerializedName("resultCode")
            @Expose
            private String resultCode;
            @SerializedName("resultMsg")
            @Expose
            private String resultMsg;

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


            public int getNumOfRows() {
                return numOfRows;
            }

            public int getPageNo() {
                return pageNo;
            }


            public int getTotalCount() {
                return totalCount;
            }



        }


    }

}
