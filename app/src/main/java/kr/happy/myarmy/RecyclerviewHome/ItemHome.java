package kr.happy.myarmy.RecyclerviewHome;

/**
 * Created by JY on 2017-04-12.
 */

public class ItemHome {

    private int img; //기업 로고
    private String title; //기업 이름
    private String t2;
    private String t3;




    public ItemHome(String title) {
        this.title = title;
    }

    public ItemHome(int img, String title,String t2, String t3) {
        this.img = img;
        this.title = title;
        this.t2 = t2;
        this.t3 = t3;

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

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }





}


