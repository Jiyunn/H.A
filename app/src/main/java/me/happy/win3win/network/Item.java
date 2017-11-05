
package me.happy.win3win.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Item   implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;

    private String thumbnail;

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
    private String gongoNo;
    @SerializedName("cyjemoknm")
    @Expose
    private String cyjemoknm;
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
    @SerializedName("eopjongGbcdNm")
    @Expose
    private String eopjongGbcdNm;
    @SerializedName("geunmujy")
    @Expose
    private String geunmujy;
    @SerializedName("geunmujysido")
    @Expose
    private String geunmujysido;
    @SerializedName("gyeongryeokGbcdNm")
    @Expose
    private String gyeongryeokGbcdNm;
    @SerializedName("gyjogeonCdNm")
    @Expose
    private String gyjogeonCdNm;
    @SerializedName("hmpgAddr")
    @Expose
    private String homepg;
    @SerializedName("jeopsubb")
    @Expose
    private String jeopsu;
    @SerializedName("jggyeyeolCdNm")
    @Expose
    private String jeonGong;
    @SerializedName("magamDt")
    @Expose
    private long magamDt;
    @SerializedName("Oegukeo")
    @Expose
    private String Oegukeo;
    @SerializedName("ogegsneungryeokCdNm")
    @Expose
    private String OegukeoGusa;
    @SerializedName("sschaeyongYn")
    @Expose
    private String sschaeyongYn;
    @SerializedName("yeokjongBrcdNm")
    @Expose
    private String yeokjongBrcdNm;
    @SerializedName("yowonGbcdNm")
    @Expose
    private String yowonGbcdNm;
    @SerializedName("yuhyoYn")
    @Expose
    private String yuhyoYn;


    protected Item(Parcel in) {
        id = in.readInt();
        thumbnail = in.readString();
        bokri = in.readString();
        ccdLDtm = in.readLong();
        cjdLDtm = in.readLong();
        cjhakryeok = in.readString();
        gongoNo = in.readString();
        cyjemoknm = in.readString();
        damdangjaFnm = in.readString();
        ddeopmuNm = in.readString();
        ddjyeonrakcheoNo = in.readString();
        dpyeonrakcheoNo = in.readString();
        eopcheNm = in.readString();
        eopjongGbcdNm = in.readString();
        geunmujy = in.readString();
        geunmujysido = in.readString();
        gyeongryeokGbcdNm = in.readString();
        gyjogeonCdNm = in.readString();
        homepg = in.readString();
        jeopsu = in.readString();
        jeonGong = in.readString();
        magamDt = in.readLong();
        Oegukeo = in.readString();
        OegukeoGusa = in.readString();
        sschaeyongYn = in.readString();
        yeokjongBrcdNm = in.readString();
        yowonGbcdNm = in.readString();
        yuhyoYn = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getBokri() {
        return bokri;
    }


    public String getCjhakryeok() {
        if (this.cjhakryeok.equals("고등학교졸업"))
            return "고졸";
        else if (this.cjhakryeok.equals("고등학교중퇴"))
            return "고교중퇴";

        return cjhakryeok;
    }


    public String getCyjemoknm() {
        return cyjemoknm;
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


    public String getEopjongGbcdNm() {
        return eopjongGbcdNm;
    }


    public String getGeunmujy() {
        return geunmujy;
    }


    /*edit genmujysideo*/
    public String convertGeunmujySido() {
        String guen = ""; //수정된 근무지 시도 나타냄
        StringBuilder builder;

        String[] temp = this.geunmujysido.split("[, /]+"); //콤마나 공백을 기준으로 문자열을 자름.

        for (int i = 0; i < temp.length; i++) {
            switch (temp[i].length()) {
                case 3: {
                    builder = new StringBuilder(temp[i]);
                    temp[i] = (builder.substring(0, 2)).toString();

                    break;
                }
                case 4: {
                    builder = new StringBuilder(temp[i]);
                    temp[i] = (builder.deleteCharAt(1).deleteCharAt(2)).toString();
                    break;
                }
                case 5: {
                    builder = new StringBuilder(temp[i]);
                    temp[i] = (builder.substring(0, 2)).toString();
                    break;
                }
            }
            if (i < temp.length - 1)
                guen += temp[i] + ", ";
            else
                guen += temp[i];
        }

        return guen;
    }

    public String getGeunmujysido() {
        return geunmujysido;
    }

    public String getGyeongryeokGbcdNm() {
        return gyeongryeokGbcdNm;
    }


    public String getGyjogeonCdNm() {
        return gyjogeonCdNm;
    }


    public String getHomepg() {
        return homepg;
    }


    public String hangleMagamDt(){
        if(this.magamDt==0)
            return "";

        else{
            SimpleDateFormat rawFormat=new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat realFormat=new SimpleDateFormat("yyyy년 MM월 dd일");
            String dDay="";

            try{
                Date date=rawFormat.parse(String.valueOf(this.magamDt));
                dDay=realFormat.format(date);

            }catch (ParseException e){
                e.printStackTrace();
            }
            return dDay;
        }

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


    public String getYeokjongBrcdNm() {
        return yeokjongBrcdNm;
    }


    public String getYowonGbcdNm() {
        return yowonGbcdNm;
    }


    public String getYuhyoYn() {
        return yuhyoYn;
    }

    public String getJeopsu() {
        return jeopsu;
    }

    public String getJeonGong() {
        return jeonGong;
    }

    public String convertJeonGong(){
        if(this.jeonGong ==null )
            return "무관";
        else
            return this.jeonGong;
    }

    public long getCcdLDtm() {
        return ccdLDtm;
    }


    public long getCjdLDtm() {
        return cjdLDtm;
    }





    public int getId() {
        return id;
    }

    public String getOegukeo() {
        return Oegukeo;
    }

    public String getOegukeoGusa() {
        return OegukeoGusa;
    }

    public String getGongoNo() {
        return gongoNo;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(thumbnail);
        dest.writeString(bokri);
        dest.writeLong(ccdLDtm);
        dest.writeLong(cjdLDtm);
        dest.writeString(cjhakryeok);
        dest.writeString(gongoNo);
        dest.writeString(cyjemoknm);
        dest.writeString(damdangjaFnm);
        dest.writeString(ddeopmuNm);
        dest.writeString(ddjyeonrakcheoNo);
        dest.writeString(dpyeonrakcheoNo);
        dest.writeString(eopcheNm);
        dest.writeString(eopjongGbcdNm);
        dest.writeString(geunmujy);
        dest.writeString(geunmujysido);
        dest.writeString(gyeongryeokGbcdNm);
        dest.writeString(gyjogeonCdNm);
        dest.writeString(homepg);
        dest.writeString(jeopsu);
        dest.writeString(jeonGong);
        dest.writeLong(magamDt);
        dest.writeString(Oegukeo);
        dest.writeString(OegukeoGusa);
        dest.writeString(sschaeyongYn);
        dest.writeString(yeokjongBrcdNm);
        dest.writeString(yowonGbcdNm);
        dest.writeString(yuhyoYn);
    }


}

