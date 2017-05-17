package kr.happy.myarmy;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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
    private String dId;
    private String dPwd;

    private String token; //Token
    private Cursor c;

    UserDBManager mDBManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.login);
        binding.setActivity(this);

        mDBManager= UserDBManager.getInstance(this);

        c=mDBManager.query(new String[]{"email"}, null,null,null,null,null);

        if(c!=null && c.moveToFirst() && c.getString(0) !=null)
            binding.loginId.setText(c.getString(0));
        c.close();


    }

    public void loginClick(View v){
        id=binding.loginId.getText().toString().trim();
        pwd=binding.loginPwd.getText().toString().trim();

        /*check id & pwd*/
         c=mDBManager.query(new String[]{"email","pwd"}, null, null, null, null, null);

        if(c !=null && c.moveToFirst()){
            dId=c.getString(0);
            dPwd=c.getString(1);
        }
        c.close();

        if(id.equals(dId) && pwd.equals(dPwd)){ //데이터베이스와 비교
            binding.warnPwd.setText("");
            callToken(ServerGenerator.getRequestService());
        }
        else
            binding.warnPwd.setText(getString(R.string.wrongPwdTwo));

    }

    /*get Token from server*/
    public void callToken(RetroInterface apiService){
        Call<Void> call=apiService.login(new RetroInterface.UserLogin(id, pwd));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    token=response.headers().get("x-auth-token");
                    try{
                        ContentValues contentValues=new ContentValues();
                        contentValues.put("token", token);
                        mDBManager.update(contentValues, "email=? ", new String[]{dId}); //토큰 디비에 저장
                    }catch (SQLException e){
                        e.printStackTrace();
                    }

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("jy", "실패!: "+t.getMessage());
            }
        });

    }

    /*when click btn , hide soft keyboard*/
    public void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
