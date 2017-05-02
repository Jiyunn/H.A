package kr.happy.myarmy;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by JY on 2017-05-02.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
