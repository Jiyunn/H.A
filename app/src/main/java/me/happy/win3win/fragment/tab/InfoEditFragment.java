package me.happy.win3win.menu;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.happy.win3win.activity.MainActivity;
import me.happy.win3win.R;
import me.happy.win3win.databinding.InfoeditBinding;
import me.happy.win3win.recyclerview.UserInfo;
import me.happy.win3win.userdb.UserDBManager;

import static me.happy.win3win.R.array.month;

/**
 * Created by JY on 2017-04-15.
 */

public class InfoEditFragment extends Fragment implements CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener {

    InfoeditBinding binding;

    private UserInfo userInfo;

    private ArrayAdapter<String> adapter;
    private List<String> year;
    private List<String> dayOfMonth;

    private ArrayList<String> wantJob;

    private UserDBManager mDBManager = null;
    private SharedPreferences pref;

    private BottomBar bottomBar;

    public InfoEditFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.infoedit, container, false);
        View view = binding.getRoot();
        binding.setInfoedit(this);
        ButterKnife.bind(this, view);


        initSpinner();
        setListeners();
        BringUserData(); //SharedPreference 객체꺼내기


        return view;
    }

    /*
    리스너 등록
     */
    private void setListeners() {
        binding.infoName.setOnFocusChangeListener(this);
        binding.infoPhone1.setOnFocusChangeListener(this);
        binding.infoPhone2.setOnFocusChangeListener(this);
        binding.infoPhone3.setOnFocusChangeListener(this);
        binding.infoAddress.setOnFocusChangeListener(this);
        binding.infoSpecialnote.setOnFocusChangeListener(this);
       /*
       경력과 특기는 다이얼로그나 새로운 프래그먼트를..
        */

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

        //자격증
        String cerNm = binding.infoCertificate.itemCertiName.getText().toString();
        userInfo.setCertificate(cerNm);
        //기타경력사항
        userInfo.setEtccareer(binding.infoEtccareer1.getText().toString() + "," + binding.infoEtccareer2.getText().toString());
        //특기사항
        userInfo.setSpecialNote(binding.infoSpecialnote.getText().toString());

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
        contentValues.put("edu", userInfo.getEdu());
        contentValues.put("wantjob", userInfo.getWantJob());
        contentValues.put("etccareer", userInfo.getEtccareer());
        contentValues.put("specialnote", userInfo.getSpecialNote());
        contentValues.put("certificate", userInfo.getCertificate());

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
        String etccareer[] = pref.getString("etccareer", "").split(",");

        binding.infoName.setText(name);

        binding.infoBirthYear.setSelection(2017 - Integer.parseInt(birth.substring(0, 4)));
        binding.infoBirthMonth.setSelection(Integer.parseInt(birth.substring(4, 6)) - 1);//month초기화하기
        binding.infoBirthDOM.setSelection(Integer.parseInt(birth.substring(6, 8)) - 1); //day초기화하기

        if (phone.length == 3) { //연락처
            binding.infoPhone1.setText(phone[0]);
            binding.infoPhone2.setText(phone[1]);
            binding.infoPhone3.setText(phone[2]);
        }
        binding.infoHakSchool.setSelection(Integer.parseInt(hak.substring(0, 1))); //학력
        binding.infoHakState.setSelection(Integer.parseInt(hak.substring(1)));

        if (etccareer.length == 2) { //경력
            binding.infoEtccareer1.setText(etccareer[0]);
            binding.infoEtccareer2.setText(etccareer[1]);
        }
        binding.infoSpecialnote.setText(pref.getString("specialnote", "")); //특기
        binding.infoAddress.setText(pref.getString("address", "")); //주소


        binding.infoCertificate.itemCertiName.setText(pref.getString("certiNm", "")); //자격증
        binding.infoCertificate.itemCertiPub.setText(pref.getString("certiPub", ""));
        binding.infoCertificate.itemCertiDt.setText(pref.getString("certiDt", ""));


        binding.infoCkGigye.setChecked(pref.getBoolean("ckGigye", false)); //희망직종
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
        editor.putString("specialnote", userInfo.getSpecialNote());
        editor.putString("etccareer", userInfo.getEtccareer());
        editor.putString("address", userInfo.getAddress());

        editor.putString("certiNm", userInfo.getCertificate());
        editor.putString("certiPub", binding.infoCertificate.itemCertiPub.getText().toString());
        editor.putString("certiDt", binding.infoCertificate.itemCertiDt.getText().toString());

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
        GregorianCalendar calendar = new GregorianCalendar();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);


        dpd.show();
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            binding.infoCertificate.itemCertiDt.setText(String.valueOf(year) + "년" +
                    " "+String.valueOf(month+1) + "월" );
            Log.d("jy", String.valueOf(year) + String.valueOf(month + 1) + String.valueOf(dayOfMonth));
        }
    };

    /*
    특기사항, 경력사항 다이얼로그
     */
    @OnClick({R.id.item_certiName, R.id.item_certiPub, R.id.info_etccareer1, R.id.info_etccareer2, R.id.info_specialnote})
    public void memoDialog( View v){
        final TextView tv= (TextView)v;

        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setTitle("내용을 입력하세요");

        View rootVIew= getActivity().getLayoutInflater().inflate(R.layout.item_info, null);
        final TextView memo=(TextView)rootVIew.findViewById(R.id.info_memo);
        memo.setText(tv.getText().toString());

        builder.setView(rootVIew);

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s=  memo.getText().toString();
                tv.setText(s);
            }
        });
        AlertDialog dialog= builder.create();
        dialog.show();
    }


    /*when click btn , hide soft keyboard*/
    @OnClick(R.id.infoparent)
    public void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /*
    스크롤 위치 조정, 포커스 받은 뷰가 상단에 오게.
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        final int y = v.getTop();
        if (hasFocus) {
            binding.infoScroll.post(new Runnable() {
                @Override
                public void run() {
                    binding.infoScroll.smoothScrollTo(0, y);
                }
            });
        }
    }

    public static  class InputDialog extends DialogFragment {


        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return super.onCreateDialog(savedInstanceState);
        }
    }

}

