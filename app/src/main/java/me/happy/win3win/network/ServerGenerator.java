package me.happy.win3win.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JY on 2017-05-06.
 */

public class ServerGenerator {

    private static final String BASE_URL = "http://win3win.me/api/v1/";
    private static final String ALL_URL = "http://apis.data.go.kr/1300000/CyJeongBo/";

    private static final int CONNECT_TIMEOUT = 10;
    private static final int WRITE_TIMEOUT =10;
    private static final int READ_TIMEOUT =10;


    public static OkHttpClient getClient() {

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS) //연결 타임아웃 시간 설정
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS) //쓰기 타임아웃 시간 설정
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS) //읽기 타임아웃 시간 설정
                .build();
    }

    public static Retrofit getAllInstance() {
        return new Retrofit.Builder()
                .baseUrl(ALL_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
    }

    public static RetroInterface getAllService() {
        return getAllInstance().create(RetroInterface.class);//retrofit 구현체에게 인터페이스의 class정보를 보내면, 서비스의 구현체가 반환된다
    }



    public static Retrofit getRequestInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
    }

    public static RetroInterface getRequestService() {
        return getRequestInstance().create(RetroInterface.class);//retrofit 구현체에게 인터페이스의 class정보를 보내면, 서비스의 구현체가 반환된다
    }


}