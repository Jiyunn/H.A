package kr.happy.myarmy.Recyclerview;

/**
 * Created by JY on 2017-04-12.
 */

public class ItemHome {

    private int img; //기업 로고
    private String title; //기업 이름
    private String content1; //설명들
    private String content2;
    private String content3;


    public ItemHome(String title) {
        this.title = title;
    }

    public ItemHome(int img, String title,String content1, String content2, String content3) {

        this.img = img;
        this.title = title;
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
    }

    public ItemHome(String title, String content1, String content2, String content3) {
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.title = title;
    }


    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent3() {
        return content3;
    }

    public void setContent3(String content3) {
        this.content3 = content3;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}


