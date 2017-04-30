package kr.happy.myarmy.Retrofit2;

import com.google.gson.annotations.SerializedName;

import java.util.Date;


public class Gongo {

    @SerializedName("bokrihs")
    String bokrihs; //복리후생

    @SerializedName("cjhakryeok")
    String cjhakryeok; //최종학력

    @SerializedName(" cygonggoNo")
    String cygonggoNo; //채용번호

    @SerializedName("damdangjaFnm")
    String damdangjaFnm; //담당자

    @SerializedName("ddjyeonrakcheoNo")
    String ddjyeonrakcheoNo; //담당자연락처

    @SerializedName("eopjongGbcdNm")
    String eopjongGbcdNm; //업종구분명

    @SerializedName("Geunmujysido")
    String Geunmujysido; //근무지 시도

    @SerializedName("Geunmujy")
    String Geunmujy; //근무지 주소

    @SerializedName("magamDt")
    Date magamDt; //마감일자

    @SerializedName("schaeyongYn")
    String sschaeyongYn;//상시채용여부


    public void setBokrihs(String bokrihs) {
        this.bokrihs = bokrihs;
    }

    public void setCjhakryeok(String cjhakryeok) {
        this.cjhakryeok = cjhakryeok;
    }

    public void setCygonggoNo(String cygonggoNo) {
        this.cygonggoNo = cygonggoNo;
    }

    public void setDamdangjaFnm(String damdangjaFnm) {
        this.damdangjaFnm = damdangjaFnm;
    }

    public void setDdjyeonrakcheoNo(String ddjyeonrakcheoNo) {
        this.ddjyeonrakcheoNo = ddjyeonrakcheoNo;
    }

    public void setEopjongGbcdNm(String eopjongGbcdNm) {
        this.eopjongGbcdNm = eopjongGbcdNm;
    }

    public void setGeunmujy(String geunmujy) {
        Geunmujy = geunmujy;
    }

    public void setGeunmujysido(String geunmujysido) {
        Geunmujysido = geunmujysido;
    }

    public void setMagamDt(Date magamDt) {
        this.magamDt = magamDt;
    }

    public void setSschaeyongYn(String sschaeyongYn) {
        this.sschaeyongYn = sschaeyongYn;
    }
}
