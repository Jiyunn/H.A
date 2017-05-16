
package kr.happy.myarmy.Server;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Item {


    @SerializedName("bokrihs")
    @Expose
    private String bokri;
    @SerializedName("ccdatabalsaengDtm")
    @Expose
    private long ccdLDtm;
    @SerializedName("cjdatabyeongyeongDtm")
    @Expose
    private long cjdLDtm;
    @SerializedName("cjhakryeok")
    @Expose
    private String cjhakryeok;
    @SerializedName("cygonggoNo")
    @Expose
    private long gongoNo;
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

    public String getBokri() {
        return bokri;
    }


    public String getCjhakryeok() {
        if (this.cjhakryeok.equals("고등학교졸업"))
            return "고졸";
        else if(this.cjhakryeok.equals("고등학교중퇴"))
            return "고교중퇴";

        return cjhakryeok;
    }



    public String getCyjemokNm() {
        return cyjemokNm;
    }



    public String getDamdangjaFnm() {
        return damdangjaFnm;
    }


    public String getDdeopmuNm() {
        return ddeopmuNm;
    }


    public String getDdjyeonrakcheoNo() {
        return ddjyeonrakcheoNo;
    }



    public String getDpyeonrakcheoNo() {
        return dpyeonrakcheoNo;
    }


    public String getEopcheNm() {
        return eopcheNm;
    }



    public long getEopjongGbcd() {
        return eopjongGbcd;
    }


    public String getEopjongGbcdNm() {
        return eopjongGbcdNm;
    }


    public String getGeunmujy() {
        return geunmujy;
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


    public long getGmjybjusoCd() {
        return gmjybjusoCd;
    }


    public String getGyeongryeokGbcdNm() {
        return gyeongryeokGbcdNm;
    }



    public String getGyjogeonCd() {
        return gyjogeonCd;
    }


    public String getGyjogeonCdNm() {
        return gyjogeonCdNm;
    }


    public String getHomepg(){
        return homepg;
    }

    public String getJeopsubb() {
        return jeopsubb;
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

    public String getSschaeyongYn() {

        return sschaeyongYn;
    }



    public String getYeokjongBrcd() {
        return yeokjongBrcd;
    }



    public String getYeokjongBrcdNm() {
        return yeokjongBrcdNm;
    }



    public long getYowonGbcd() {
        return yowonGbcd;
    }



    public String getYowonGbcdNm() {
        return yowonGbcdNm;
    }


    public String getYuhyoYn() {
        return yuhyoYn;
    }



    public long getCcdLDtm() {
        return ccdLDtm;
    }


    public long getCjdLDtm() {
        return cjdLDtm;
    }


    public long getGongoNo() {
        return gongoNo;
    }

}
