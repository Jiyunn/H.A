package me.happy.win3win.activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.happy.win3win.R;
import me.happy.win3win.databinding.ActivitySearchBinding;
import me.happy.win3win.db.UserDBManager;
import me.happy.win3win.fragment.search.KeywordFragment;
import me.happy.win3win.fragment.search.SearchResultFragment;
import me.happy.win3win.fragment.tab.model.Gonggo;

/**
 * Created by JY on 2017-05-22.
 */

public class SearchActivity extends AppCompatActivity implements TextWatcher, TextView.OnEditorActionListener {

    private ActivitySearchBinding binding;
    private List<Gonggo> dataSet;
    private String query = "";

    private UserDBManager mDBManger;
    private FragmentManager fgManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSet = new ArrayList<>();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        ButterKnife.bind(this);
        binding.setActivity(this);

        mDBManger= UserDBManager.getInstance(this);

        // 초기 화면
        fgManager = getSupportFragmentManager();
        fgManager
                .beginTransaction()
                .replace(R.id.sfrag, new KeywordFragment())
                .commit();

        binding.searchSearch.toolbarSearch.addTextChangedListener(this);
        binding.searchSearch.toolbarSearch.setOnEditorActionListener(this);
    }

    /*
    백버튼 누르면 홈화면으로 가기
     */
    @OnClick(R.id.toolbar_back2)
    public void searchBackClick(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    /*
    검색 버튼 누르면 결과 보여주기
     */
    @OnClick(R.id.toolbar_go) //여기서 savedInstance에 검색한 텍스트만 가져가기기
    public void goToSearchResu() {
        FragmentManager fgManager = getSupportFragmentManager();
        SearchResultFragment searchResultFragment = new SearchResultFragment();

        ContentValues contentValues =new ContentValues();
        String s="";

        Cursor c= mDBManger.query(new String[]{"keyword"} , null, null, null, null, null);

        if(c !=null && c.moveToFirst()) {
            s = c.getString(0);

        }
        contentValues.put("keyword",  s+","+query);

        mDBManger.update(contentValues, "_id= ? ", new String[]{"1"});


        fgManager
                .beginTransaction()
                .replace(R.id.sfrag, searchResultFragment.newInstance(query))
                .commit();
    }

    /*
    검색하는 글자 치는중
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        query = s.toString();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onEditorAction(TextView v, int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_SEARCH  || keyCode == EditorInfo.IME_ACTION_SEARCH) {
            hideSoftKeyboard(v);
            goToSearchResu();
        }
        return false;
    }

    /*when click btn , hide soft keyboard*/
    public void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    }
