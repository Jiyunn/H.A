package kr.happy.myarmy.Server;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JY on 2017-05-06.
 */

public class ServerGenerator {

    static final String BASE_URL = "http://win3win.me/api/v1/";


    public static OkHttpClient getClient() {

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
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