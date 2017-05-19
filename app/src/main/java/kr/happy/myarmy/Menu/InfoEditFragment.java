package kr.happy.myarmy.Menu;

import android.content.ContentValues;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roughike.bottombar.BottomBar;

import java.util.ArrayList;

import kr.happy.myarmy.MainActivity;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.Data;
import kr.happy.myarmy.Recyclerview.InfoAdapter;
import kr.happy.myarmy.UserDB.UserDBManager;
import kr.happy.myarmy.databinding.InfoeditBinding;

/**
 * Created by JY on 2017-04-15.
 */

public class InfoEditFragment extends Fragment  {

    InfoeditBinding binding;

    private InfoAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Data> dataSet;
    private String[] itemName; //항목 이름들
    private String[] itemContent; //항목 내용들

    private String[] columns; //데이터베이스 컬럼
    private FragmentManager fgManager;

    public UserDBManager mDBManager=null;

    private BottomBar bottomBar;

    public InfoEditFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.infoedit, container, false);
        View view = binding.getRoot();

        setUserData(); //디비에서 데이터 받아와서 세팅

        binding.rvInfo.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //세로로 뿌리기
        mLayoutManager.setStackFromEnd(true);
        mLayoutManager.scrollToPosition(0); //firstScrollposition

        binding.rvInfo.setLayoutManager(mLayoutManager);

        adapter = new InfoAdapter(getActivity(), dataSet, R.layout.item_info, mLayoutManager, binding.rvInfo, fgManager); //어댑터 등록
        adapter.setmItemContent(itemContent); //변경될 내용, 즉 정보를 담은 배열을 넘겨줌

        binding.rvInfo.setAdapter(adapter);

        binding.rvInfo.setItemAnimator(new DefaultItemAnimator());


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bottomBar=(BottomBar)(((MainActivity)getContext()).findViewById(R.id.bottom_bar)); //바텀바 안보이게
        bottomBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPause() { //여기서 데이터 작업할거.
        updateUserData();
        bottomBar.setVisibility(View.VISIBLE);
        super.onPause();
    }

    /* update user data */
    public void updateUserData() {;
        itemContent=adapter.getmItemContent();
        ContentValues contentValues = new ContentValues();

        for(int i=0; i<binding.rvInfo.getAdapter().getItemCount(); i++){
            contentValues.put(columns[i + 1], itemContent[i]);
        }
        mDBManager.update(contentValues, "_id=? ", new String[]{"1"});
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBManager=UserDBManager.getInstance(getActivity()); //dbmanager
        fgManager= getFragmentManager();
    }

    /*set data*/
    public void setUserData() {
        columns=new String[]{"email","name", "birth", "wantjob", "specialnote", "certificate","edu", "address", "etccareer", "phone"}; //받아올 컬럼들

        itemName = new String[]{getString(R.string.name), getString(R.string.birth), getString(R.string.wantjob), getString(R.string.specialnote),
                getString(R.string.certificate), getString(R.string.education), getString(R.string.address),getString(R.string.etccareer), getString(R.string.phone)}; //항목이름

        itemContent=new String[itemName.length]; //내용 배열
        dataSet = new ArrayList<>(); //뿌려줄 데이터를 담는 리스트

        Cursor c=mDBManager.query(columns, null, null, null, null, null);

        if ( c != null  && c.moveToFirst()) {
            do {
                for (int i = 0; i < itemName.length; i++) {
                    itemContent[i] = c.getString(i+1);
                }
            } while (c.moveToNext());
        }
        c.close();

        for (int i = 0; i < itemName.length; i++) { //데이터 넣어주기.
            dataSet.add(new Data(itemName[i], itemContent[i]));
        }
    }
}
