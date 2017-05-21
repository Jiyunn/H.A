package kr.happy.myarmy;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import kr.happy.myarmy.Custom.BackButtonHandler;
import kr.happy.myarmy.Server.RetroInterface;
import kr.happy.myarmy.Server.ServerGenerator;
import kr.happy.myarmy.UserDB.UserDBManager;
import kr.happy.myarmy.databinding.LoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    LoginBinding binding;

    private String id;
    private String pwd;
    private String token; //Token

    UserDBManager mDBManager;
    BackButtonHandler backButtonHandler;


    @Override
    public void onBackPressed() {
        backButtonHandler.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.login);
        binding.setActivity(this);

        mDBManager = UserDBManager.getInstance(this);
        backButtonHandler= new BackButtonHandler(this);

        /*
        아이디 띄워주기
         */
        Cursor c = mDBManager.query(new String[]{"email"}, null, null, null, null, null);

        if(c !=null && c.moveToFirst())
            binding.loginId.setText(c.getString(0));

    }

    /*
    액티비티 onstop에서 UI상태저장
     */


    public void loginClick(View v) {
        id = binding.loginId.getText().toString().trim();
        pwd = binding.loginPwd.getText().toString().trim();

        binding.warnPwd.setText("");
        callToken(ServerGenerator.getRequestService());
    }

    /*get Token from server*/
    public void callToken(RetroInterface apiService) {
        Call<Void> call = apiService.login(new RetroInterface.UserLogin(id, pwd));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    token = response.headers().get("x-auth-token");

                    ContentValues contentValues = new ContentValues();
                    contentValues.put("token", token);
                    contentValues.put("email", id);

                    Cursor c = mDBManager.query(new String[]{"_id", "email"}, null, null, null, null, null);

                    mDBManager.update(contentValues, "_id= ? ", new String[]{"1"}); //토큰 디비에 저장
                    c.close();

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                binding.warnPwd.setText(getString(R.string.wrongPwdTwo));
            }
        });

    }

    /*when click btn , hide soft keyboard*/
    public void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


}
