package kr.happy.myarmy.Server;

import com.facebook.stetho.okhttp3.BuildConfig;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JY on 2017-05-06.
 */

public class ServerGenerator {

    static final String BASE_URL = "http://apis.data.go.kr/1300000/CyJeongBo/";
    static final String REQUEST_URL="http://win3win.me/api/v1/";


    public static OkHttpClient getClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .tag("LoggingI")
                        .request("Request")
                        .response("response")
                        .addHeader("version", BuildConfig.VERSION_NAME)
                        .build());
        return client.build();
    }

    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder() //기본 url설정과 build.
                .baseUrl(BASE_URL) //요청하는 서버의 도메인
                .addConverterFactory(GsonConverterFactory.create())    //Gson컨버터를 추가해서, retrofit이 응답을 json으로 받을 수 있도록하기
                .client(getClient())
                .build();
    }

    public static Retrofit getRequestInstance(){
        return new Retrofit.Builder()
                .baseUrl(REQUEST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
    }

    public static RetroInterface getRequestService(){
        return getRequestInstance().create(RetroInterface.class);
    }

    public static RetroInterface getAPIService(){
        return getRetrofitInstance().create(RetroInterface.class); //retrofit 구현체에게 인터페이스의 class정보를 보내면, 서비스의 구현체가 반환된다
    }
}