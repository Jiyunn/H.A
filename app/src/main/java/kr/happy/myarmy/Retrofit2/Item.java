
package kr.happy.myarmy.Retrofit2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Item {

    @SerializedName("bokrihs")
    @Expose
    private String bokrihs;
    @SerializedName("ccdatabalsaengDtm")
    @Expose
    private long ccdatabalsaengDtm;
    @SerializedName("cjdatabyeongyeongDtm")
    @Expose
    private long cjdatabyeongyeongDtm;
    @SerializedName("cjhakryeok")
    @Expose
    private String cjhakryeok;
    @SerializedName("cygonggoNo")
    @Expose
    private long cygonggoNo;
    @SerializedName("cyjemokNm")
    @Expose
    private String cyjemokNm;
    @SerializedName("damdangjaFnm")
    @Expose
    private String damdangjaFnm;
    @SerializedName("ddeopmuNm")
    @Expose
    private String ddeopmuNm;
    @SerializedName("ddjyeonrakcheoNo")
    @Expose
    private String ddjyeonrakcheoNo;
    @SerializedName("dpyeonrakcheoNo")
    @Expose
    private String dpyeonrakcheoNo;
    @SerializedName("eopcheNm")
    @Expose
    private String eopcheNm;
    @SerializedName("eopjongGbcd")
    @Expose
    private long eopjongGbcd;
    @SerializedName("eopjongGbcdNm")
    @Expose
    private String eopjongGbcdNm;
    @SerializedName("geunmujy")
    @Expose
    private String geunmujy;
    @SerializedName("geunmujysido")
    @Expose
    private String geunmujysido;
    @SerializedName("gmjybjusoCd")
    @Expose
    private long gmjybjusoCd;
    @SerializedName("gyeongryeokGbcdNm")
    @Expose
    private String gyeongryeokGbcdNm;
    @SerializedName("gyjogeonCd")
    @Expose
    private String gyjogeonCd;
    @SerializedName("gyjogeonCdNm")
    @Expose
    private String gyjogeonCdNm;
    @SerializedName("hmpgAddr")
    @Expose
    private String homepg;
    @SerializedName("jeopsubb")
    @Expose
    private String jeopsubb;
    @SerializedName("magamDt")
    @Expose
    private long magamDt;
    @SerializedName("sschaeyongYn")
    @Expose
    private String sschaeyongYn;
    @SerializedName("yeokjongBrcd")
    @Expose
    private String yeokjongBrcd;
    @SerializedName("yeokjongBrcdNm")
    @Expose
    private String yeokjongBrcdNm;
    @SerializedName("yowonGbcd")
    @Expose
    private long yowonGbcd;
    @SerializedName("yowonGbcdNm")
    @Expose
    private String yowonGbcdNm;
    @SerializedName("yuhyoYn")
    @Expose
    private String yuhyoYn;

    public String getBokrihs() {
        return bokrihs;
    }

    public void setBokrihs(String bokrihs) {
        this.bokrihs = bokrihs;
    }

    public long getCcdatabalsaengDtm() {
        return ccdatabalsaengDtm;
    }

    public void setCcdatabalsaengDtm(long ccdatabalsaengDtm) {
        this.ccdatabalsaengDtm = ccdatabalsaengDtm;
    }

    public long getCjdatabyeongyeongDtm() {
        return cjdatabyeongyeongDtm;
    }

    public void setCjdatabyeongyeongDtm(long cjdatabyeongyeongDtm) {
        this.cjdatabyeongyeongDtm = cjdatabyeongyeongDtm;
    }

    public String getCjhakryeok() {
        if (this.cjhakryeok.equals("고등학교졸업"))
            return "고졸";

        return cjhakryeok;
    }

    public void setCjhakryeok(String cjhakryeok) {

    }

    public long getCygonggoNo() {
        return cygonggoNo;
    }

    public void setCygonggoNo(long cygonggoNo) {
        this.cygonggoNo = cygonggoNo;
    }

    public String getCyjemokNm() {
        return cyjemokNm;
    }

    public void setCyjemokNm(String cyjemokNm) {
        this.cyjemokNm = cyjemokNm;
    }

    public String getDamdangjaFnm() {
        return damdangjaFnm;
    }

    public void setDamdangjaFnm(String damdangjaFnm) {
        this.damdangjaFnm = damdangjaFnm;
    }

    public String getDdeopmuNm() {
        return ddeopmuNm;
    }

    public void setDdeopmuNm(String ddeopmuNm) {
        this.ddeopmuNm = ddeopmuNm;
    }

    public String getDdjyeonrakcheoNo() {
        return ddjyeonrakcheoNo;
    }

    public void setDdjyeonrakcheoNo(String ddjyeonrakcheoNo) {
        this.ddjyeonrakcheoNo = ddjyeonrakcheoNo;
    }

    public String getDpyeonrakcheoNo() {
        return dpyeonrakcheoNo;
    }

    public void setDpyeonrakcheoNo(String dpyeonrakcheoNo) {
        this.dpyeonrakcheoNo = dpyeonrakcheoNo;
    }

    public String getEopcheNm() {
        return eopcheNm;
    }

    public void setEopcheNm(String eopcheNm) {
        this.eopcheNm = eopcheNm;
    }

    public long getEopjongGbcd() {
        return eopjongGbcd;
    }

    public void setEopjongGbcd(long eopjongGbcd) {
        this.eopjongGbcd = eopjongGbcd;
    }

    public String getEopjongGbcdNm() {
        return eopjongGbcdNm;
    }

    public void setEopjongGbcdNm(String eopjongGbcdNm) {
        this.eopjongGbcdNm = eopjongGbcdNm;
    }

    public String getGeunmujy() {
        return geunmujy;
    }

    public void setGeunmujy(String geunmujy) {
        this.geunmujy = geunmujy;
    }


    /*edit genmujysideo*/
    public String convertGeunmujySido(){
        String guen=""; //수정된 근무지 시도 나타냄
        StringBuilder builder;

        String[] temp=this.geunmujysido.split("[, ]+"); //콤마나 공백을 기준으로 문자열을 자름.

        for(int i=0; i<temp.length; i++){
            switch(temp[i].length()){
                case 3:{
                    builder=new StringBuilder(temp[i]);
                    temp[i]=(builder.substring(0, 2)).toString();

                    break;
                }
                case 4:{
                    builder=new StringBuilder(temp[i]);
                    temp[i]=(builder.deleteCharAt(1).deleteCharAt(2)).toString();
                    break;
                }
                case 5:{
                    builder=new StringBuilder(temp[i]);
                    temp[i]=(builder.substring(0, 2)).toString();
                    break;
                }
            }
            if(i<temp.length-1)
                guen+=temp[i]+", ";
            else
                guen+=temp[i];
        }

        return guen;
    }

    public String getGeunmujysido() {
        return geunmujysido;
    }

    public void setGeunmujysido(String geunmujysido) {
        this.geunmujysido = geunmujysido;
    }

    public long getGmjybjusoCd() {
        return gmjybjusoCd;
    }

    public void setGmjybjusoCd(long gmjybjusoCd) {
        this.gmjybjusoCd = gmjybjusoCd;
    }

    public String getGyeongryeokGbcdNm() {
        return gyeongryeokGbcdNm;
    }

    public void setGyeongryeokGbcdNm(String gyeongryeokGbcdNm) {
        this.gyeongryeokGbcdNm = gyeongryeokGbcdNm;
    }

    public String getGyjogeonCd() {
        return gyjogeonCd;
    }

    public void setGyjogeonCd(String gyjogeonCd) {
        this.gyjogeonCd = gyjogeonCd;
    }

    public String getGyjogeonCdNm() {
        return gyjogeonCdNm;
    }

    public void setGyjogeonCdNm(String gyjogeonCdNm) {
        this.gyjogeonCdNm = gyjogeonCdNm;
    }

    public void setHomepg (String homepg){
        this.homepg=homepg;
    }
    public String getHomepg(){
        return homepg;
    }

    public String getJeopsubb() {
        return jeopsubb;
    }

    public void setJeopsubb(String jeopsubb) {
        this.jeopsubb = jeopsubb;
    }


    public String convertMagamDt() {//마감일자
        if (this.magamDt == 0) //마감일자가 없으면 상시채용 이므로, 상시채용 글씨리턴.
            return "";

        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Calendar currentTime = Calendar.getInstance(Locale.KOREA); //현재시간 얻어오기
            Calendar mgamTime = Calendar.getInstance();
            String dDay = "";

            try {
                mgamTime.setTime(dateFormat.parse(String.valueOf(this.magamDt))); //마감일을 시간으로 변경.
                long day = (mgamTime.getTimeInMillis() - currentTime.getTimeInMillis()) / (24 * 3600 * 1000);

                if (day == 0)
                    dDay = "내일마감";
                else
                    dDay = "D-" + String.valueOf(day + 1);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            return dDay;
        }
    }

    public long getMagamDt() {
        return magamDt;
    }

    public void setMagamDt(long magamDt) {
        this.magamDt = magamDt;
    }

    public String getSschaeyongYn() { //상시채용여부

        return sschaeyongYn;
    }

    public void setSschaeyongYn(String sschaeyongYn) {

    }

    public String getYeokjongBrcd() {
        return yeokjongBrcd;
    }

    public void setYeokjongBrcd(String yeokjongBrcd) {
        this.yeokjongBrcd = yeokjongBrcd;
    }

    public String getYeokjongBrcdNm() {
        return yeokjongBrcdNm;
    }

    public void setYeokjongBrcdNm(String yeokjongBrcdNm) {
        this.yeokjongBrcdNm = yeokjongBrcdNm;
    }

    public long getYowonGbcd() {
        return yowonGbcd;
    }

    public void setYowonGbcd(long yowonGbcd) {
        this.yowonGbcd = yowonGbcd;
    }

    public String getYowonGbcdNm() {
        return yowonGbcdNm;
    }

    public void setYowonGbcdNm(String yowonGbcdNm) {
        this.yowonGbcdNm = yowonGbcdNm;
    }

    public String getYuhyoYn() {
        return yuhyoYn;
    }

    public void setYuhyoYn(String yuhyoYn) {
        this.yuhyoYn = yuhyoYn;
    }

}
