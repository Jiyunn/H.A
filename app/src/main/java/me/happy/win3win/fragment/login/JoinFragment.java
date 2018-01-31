package me.happy.win3win.fragment.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.happy.win3win.R;
import me.happy.win3win.databinding.FragmentJoinBinding;
import me.happy.win3win.db.UserDBManager;
import me.happy.win3win.network.RetroInterface;
import me.happy.win3win.network.ServerGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JY on 2017-05-05.
 */

public class JoinFragment extends Fragment {

    FragmentJoinBinding binding;

    String username;
    String pwd;
    String code;
    String email;
    String birth;

    UserDBManager mDBManager;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBManager = UserDBManager.getInstance(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_join, container, false);
        binding.setFragment(this);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        binding.signupAEmail.addTextChangedListener(new SignupTextWatcher(binding.signupAEmail));
        binding.signupEmail.addTextChangedListener(new SignupTextWatcher(binding.signupEmail));
        binding.signupPwd.addTextChangedListener(new SignupTextWatcher(binding.signupPwd));
        binding.signupBirth.addTextChangedListener(new SignupTextWatcher(binding.signupBirth));


        return view;
    }


    /*
    When Click Join Btn
    */
    @OnClick({R.id.signup_sendEmail, R.id.join})
    public void onJoinClick(View v) {
        username = binding.signupName.getText().toString().trim();
        email = binding.signupEmail.getText().toString();
        pwd = binding.signupPwd.getText().toString().trim();
        birth=binding.signupBirth.getText().toString().trim();

        if (binding.signupAEmail.getText().equals(" 인증번호 불일치"))
            confirmDialog("올바르지 않은 인증번호 입니다");

        if (username == null || email == null || pwd == null || birth==null
                || username.trim().length() == 0 || email.trim().length() == 0 || pwd.trim().length() == 0 || birth.trim().length()==0)
            confirmDialog("모든 항목을 입력해주세요");
        else
            callJoinAPI(ServerGenerator.getRequestService());
    }


    /* request join*/
    public void callJoinAPI(RetroInterface apiService) {

        Call<JsonObject> call = apiService.requestJoin(new RetroInterface.User(email, username, pwd));

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    /*
                    save user's profile
                     */
                    try {
                        ContentValues contentValues=new ContentValues();

                        contentValues.put("email", email);
                        contentValues.put("pwd", pwd);
                        contentValues.put("name", username);
                        contentValues.put("birth", birth);

                        mDBManager.update(contentValues, "_id= ? ", new String[]{"1"});

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    confirmDialog("회원가입이 완료되었습니다");

//                    Handler handler= new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            startActivity(new Intent(getContext(), LoginActivity.class));
//                        }
//                    },2000);

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
               t.printStackTrace();
            }
        });
    }


    /*send email auth code*/
    public void sendCodeClick(View v) {
        hideSoftKeyboard(v);

        email = binding.signupEmail.getText().toString();

        if (email.trim().length() > 0 &&
                !binding.warnMail.getText().equals(getString(R.string.wrongEmail))) {
            callMailAPI(ServerGenerator.getRequestService());
        }
    }

    /*send email*/
    public void callMailAPI(RetroInterface apiService) {
        Call<JsonObject> call = apiService.sendMail(email);

        confirmDialog("인증번호가 발송되었습니다");

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    try {
                        code = response.body().get("result").getAsString(); //코드 나도 가질거야
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /*show dialog*/
    private void confirmDialog(String msg) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setMessage(msg)
                .setNegativeButton("확인", null)
                .create();

        dialog.show();
    }

    /*when click btn , hide soft keyboard*/
    @OnClick(R.id.joinparent)
    public void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    /* check edittext's text*/
    private class SignupTextWatcher implements TextWatcher {

        View view;

        SignupTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (view.getId()) {

                case R.id.signup_email: {
                    String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";

                    if (!s.toString().matches(regex))
                        binding.warnMail.setText(getString(R.string.wrongEmail));
                    else
                        binding.warnMail.setText(" 기재된 이메일주소로 인증번호가 전송됩니다");
                    break;
                }
                case R.id.signup_aEmail: {
                    if (code != null) {
                        if (s.toString().equals(code))
                            binding.warnMail.setText(" 인증번호 일치");
                        else
                            binding.warnMail.setText(" 인증번호 불일치");
                    }
                    break;
                }
                case R.id.signup_birth: {
                    String regex = "^(?:(19)|(20))(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$";

                    if (!s.toString().matches(regex))
                        binding.warnBirth.setText(" ex)19980413과 같은 형식으로 입력해주세요");
                    else
                        binding.warnBirth.setText("");
                }

                case R.id.signup_pwd: {
                    String tempPwd = s.toString().trim();
                    if (tempPwd.length() < 8 || tempPwd.length() >= 50)
                        binding.warnPwd.setText(getString(R.string.wrongPwd));
                    else
                        binding.warnPwd.setText("");
                    break;
                }


            }
        }
    }
}
