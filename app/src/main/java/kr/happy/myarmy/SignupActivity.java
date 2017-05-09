package kr.happy.myarmy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import kr.happy.myarmy.databinding.SignupBinding;

/**
 * Created by JY on 2017-05-05.
 */

public class SignupActivity extends AppCompatActivity {

    SignupBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.signup);
    }
}
