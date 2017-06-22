package me.happy.win3win.Splash;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.happy.win3win.MainActivity;
import me.happy.win3win.R;
import me.happy.win3win.Server.RetroInterface;
import me.happy.win3win.Server.ServerGenerator;
import me.happy.win3win.UserDB.UserDBManager;
import me.happy.win3win.databinding.LoginBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    private LoginBinding binding;

    private String id;
    private String pwd;
    private String token; //Token

    UserDBManager mDBManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBManager = UserDBManager.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.login, container, false);
        binding.setLogin(this);
        View view = binding.getRoot();
        ButterKnife.bind(this,view);

        /*
        아이디 띄워주기
         */
        Cursor c = mDBManager.query(new String[]{"email"}, null, null, null, null, null);

        if (c != null && c.moveToFirst())
            binding.loginId.setText(c.getString(0));

        return view;
    }

    /*
    액티비티 onstop에서 UI상태저장
     */
    @OnClick(R.id.login_signIn)
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

                    startActivity(new Intent(getContext().getApplicationContext(), MainActivity.class));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                binding.warnPwd.setText(getString(R.string.wrongPwdTwo));
            }
        });

    }

    /*when click btn , hide soft keyboard*/
    @OnClick(R.id.loginParent)
    public void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


}
