package kr.happy.myarmy;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kr.happy.myarmy.Custom.BackButtonHandler;
import kr.happy.myarmy.UserDB.UserDBManager;
import kr.happy.myarmy.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    UserDBManager mDBManager;
    Cursor c;

    BackButtonHandler backButtonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setActivity(this);

        backButtonHandler=new BackButtonHandler(this);

        mDBManager=UserDBManager.getInstance(this);


        c=mDBManager.query(new String[]{"token"}, null, null, null, null, null);

//        if (c.getString(0) !=null )
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }

    @Override
    protected void onStop() {
        super.onStop();
        c.close();
    }

    public void onClick(View v) {
        if (v.getId()==R.id.signIn)
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        else if(v.getId() == R.id.signUp)
            startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }



    @Override
    public void onBackPressed() {
        backButtonHandler.onBackPressed();
    }


}
