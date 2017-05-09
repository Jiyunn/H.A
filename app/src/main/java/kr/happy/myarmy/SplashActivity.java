package kr.happy.myarmy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import kr.happy.myarmy.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setActivity(this);

    }
    public void onClick(View v) {
        if (v.getId()==R.id.signIn)
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        else if(v.getId() == R.id.signUp)
            startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }


}
