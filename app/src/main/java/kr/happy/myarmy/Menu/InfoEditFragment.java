package kr.happy.myarmy.Menu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.happy.myarmy.MainActivity;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.UserInfo;
import kr.happy.myarmy.UserDB.UserDBManager;
import kr.happy.myarmy.databinding.InfoeditBinding;

import static kr.happy.myarmy.R.array.month;

/**
 * Created by JY on 2017-04-15.
 */

public class InfoEditFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    InfoeditBinding binding;

    private UserInfo userInfo;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> year;
    private ArrayList<String> dayOfMonth;

    private ArrayList<String> wantJob;

    private UserDBManager mDBManager = null;
    private SharedPreferences pref;

    private BottomBar bottomBar;

    public InfoEditFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("jy", "ㅇㅇ?");
        binding = DataBindingUtil.inflate(inflater, R.layout.infoedit, container, false);
        View view = binding.getRoot();
        binding.setInfoedit(this);
        ButterKnife.bind(this, view);

        binding.infoScroll.setSmoothScrollingEnabled(true);

        initSpinner();
        setCKCheckListener();
        BringUserData(); //SharedPreference 객체꺼내기


        return view;
    }
    /*
    리스너 등록
     */
    private void setCKCheckListener(){
        binding.infoCkGigye.setOnCheckedChangeListener(this);
        binding.infoCkJeonja.setOnCheckedChangeListener(this);
        binding.infoCkJeongi.setOnCheckedChangeListener(this);
        binding.infoCkWhahak.setOnCheckedChangeListener(this);
        binding.infoCkCheolgang.setOnCheckedChangeListener(this);
        binding.infoCkGaebal.setOnCheckedChangeListener(this);
        binding.infoCkIT.setOnCheckedChangeListener(this);
        binding.infoCkSomyou.setOnCheckedChangeListener(this);
        binding.infoCkEuiyak.setOnCheckedChangeListener(this);
        binding.infoCkSaengsan.setOnCheckedChangeListener(this);
        binding.infoCkEuiryu.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton v, boolean isChecked) {
        if (isChecked)
            wantJob.add(wantJob.size(), v.getText().toString().substring(0, 2));

        else if (!isChecked)
            wantJob.remove(v.getText().toString().substring(0, 2));

    }

    /*
    save user data, 데이터를 USERINFO객체에 저장하고, UI저장은 그 객체 내용을 불러오는 걸로~
     */
    protected void saveUserData() {

        userInfo.setName(binding.infoName.getText().toString()); //이름

        String year = binding.infoBirthYear.getSelectedItem().toString().replace("년", ""); //생일
        String month = binding.infoBirthMonth.getSelectedItem().toString().replace("월", "");
        String dom = binding.infoBirthDOM.getSelectedItem().toString().replace("일", "");
        userInfo.setBirth(year + month + dom);

        //최종학력
        userInfo.setEdu(binding.infoHakSchool.getSelectedItem().toString() + "," + binding.infoHakState.getSelectedItem().toString());
        //거주지역
        userInfo.setAddress(binding.infoAddress.getText().toString());
        //핸드폰
        userInfo.setPhone(binding.infoPhone1.getText().toString().trim() + "-" + binding.infoPhone2.getText().toString().trim() + "-" + binding.infoPhone3.getText().toString().trim());
        //희망직종
        String s = Arrays.toString(wantJob.toArray());
        userInfo.setWantJob(s.substring(1, s.length() - 1));
        Log.d("jy", userInfo.getWantJob());
    }

    /*
      UPDATE USER TABLE
      */
    public void updateUserData() {
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", userInfo.getName());
        contentValues.put("birth", userInfo.getBirth());
        contentValues.put("phone", userInfo.getPhone());
        contentValues.put("address", userInfo.getAddress());
        contentValues.put("edu",  userInfo.getEdu());
        contentValues.put("wantjob", userInfo.getWantJob());

        mDBManager.update(contentValues, "_id=? ", new String[]{"1"});
    }


    @Override
    public void onPause() {
        saveUserData(); //유저데이터를 객체에 저장
        updateUserData(); //유저데이터를 데이터베이스에 저장
        savePreference(); //유저의 UI상태저장

        super.onPause();
    }

    @Override
    public void onStop() {
        bottomBar.setVisibility(View.VISIBLE);
        super.onStop();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userInfo = new UserInfo();
        mDBManager = UserDBManager.getInstance(getActivity()); //dbmanager
        pref = getActivity().getSharedPreferences("pref", Activity.MODE_PRIVATE);
        wantJob = new ArrayList<>();

    }

    /*
    유저의 UI
    */
    public void BringUserData() {
        Cursor c = mDBManager.query(new String[]{"email"}, null, null, null, null, null);

        if (c != null && c.moveToFirst())
            binding.id.setText(c.getString(0));
        c.close();

        String name = pref.getString("name", "");
        String birth = pref.getString("birth", "20170101");
        String phone[] = pref.getString("phone", "010").split("-");
        String hak = pref.getString("hak", "40");

        binding.infoName.setText(name);

        binding.infoBirthYear.setSelection(2017 - Integer.parseInt(birth.substring(0, 4)));
        binding.infoBirthMonth.setSelection(Integer.parseInt(birth.substring(4, 6)) - 1);//month초기화하기
        binding.infoBirthDOM.setSelection(Integer.parseInt(birth.substring(6, 8)) - 1); //day초기화하기

        if (phone.length == 3) {
            binding.infoPhone1.setText(phone[0]);
            binding.infoPhone2.setText(phone[1]);
            binding.infoPhone3.setText(phone[2]);
        }

        binding.infoHakSchool.setSelection(Integer.parseInt(hak.substring(0, 1)));
        binding.infoHakState.setSelection(Integer.parseInt(hak.substring(1)));


        binding.infoCkGigye.setChecked(pref.getBoolean("ckGigye", false));
        binding.infoCkJeongi.setChecked(pref.getBoolean("ckJeongi", false));
        binding.infoCkJeonja.setChecked(pref.getBoolean("ckJeonja", false));
        binding.infoCkWhahak.setChecked(pref.getBoolean("ckWha", false));
        binding.infoCkCheolgang.setChecked(pref.getBoolean("ckCheol", false));
        binding.infoCkGaebal.setChecked(pref.getBoolean("ckGae", false));
        binding.infoCkIT.setChecked(pref.getBoolean("ckIT", false));
        binding.infoCkSomyou.setChecked(pref.getBoolean("ckSomyou", false));
        binding.infoCkEuiyak.setChecked(pref.getBoolean("ckEuiyak", false));
        binding.infoCkSaengsan.setChecked(pref.getBoolean("ckSaeng", false));
        binding.infoCkEuiryu.setChecked(pref.getBoolean("ckEuiryu", false));
    }

    /*
    initialize spinner
     */
    public void initSpinner() {
        year = new ArrayList<>();
        dayOfMonth = new ArrayList<>();

        for (int i = 0; i < 40; i++)
            year.add(i, String.valueOf(2017 - i) + "년");

        for (int i = 0; i < 30; i++) {
            if (i < 9)
                dayOfMonth.add(i, "0" + String.valueOf(i + 1) + "일");
            else
                dayOfMonth.add(i, String.valueOf(i + 1) + "일");
        }

        adapter = new ArrayAdapter<>(getActivity(), R.layout.xspinner, year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.infoBirthYear.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.xspinner, getResources().getStringArray(month));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.infoBirthMonth.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.xspinner, dayOfMonth);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.infoBirthDOM.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.xspinner, getResources().getStringArray(R.array.school));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.infoHakSchool.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), R.layout.xspinner, getResources().getStringArray(R.array.schoolState));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.infoHakState.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bottomBar = (BottomBar) (((MainActivity) getContext()).findViewById(R.id.bottom_bar)); //바텀바 안보이게
        bottomBar.setVisibility(View.INVISIBLE);
    }


    /*
   UI상태저장
    */
    protected void savePreference() {
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("name", userInfo.getName());
        editor.putString("birth", userInfo.getBirth());
        editor.putString("phone", userInfo.getPhone());

        editor.putBoolean("ckGigye", binding.infoCkGigye.isChecked());
        editor.putBoolean("ckJeonja", binding.infoCkJeonja.isChecked());
        editor.putBoolean("ckJeongi", binding.infoCkJeongi.isChecked());
        editor.putBoolean("ckWha", binding.infoCkWhahak.isChecked());
        editor.putBoolean("ckCheol", binding.infoCkCheolgang.isChecked());
        editor.putBoolean("ckGae", binding.infoCkGaebal.isChecked());
        editor.putBoolean("ckIT", binding.infoCkIT.isChecked());
        editor.putBoolean("ckSomyou", binding.infoCkSomyou.isChecked());
        editor.putBoolean("ckEuiyak", binding.infoCkEuiyak.isChecked());
        editor.putBoolean("ckSaeng", binding.infoCkSaengsan.isChecked());
        editor.putBoolean("ckEuiryu", binding.infoCkEuiryu.isChecked());

        editor.putString("hak", String.valueOf(binding.infoHakSchool.getSelectedItemPosition()) +
                String.valueOf(binding.infoHakState.getSelectedItemPosition()));

        editor.commit();
    }

    /*
    금일로 데이트피커 다이얼로그
     */
    @OnClick(R.id.item_cerDtBtn)
    protected void dateDialog() {
            Log.d("jy", "클릭ㅎ?");
            GregorianCalendar calendar = new GregorianCalendar();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dpd = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);

            dpd.show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            Log.d("jy", String.valueOf(year) + String.valueOf(month+1) + String.valueOf(dayOfMonth));
        }
    };


    /*when click btn , hide soft keyboard*/
    public void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


}

