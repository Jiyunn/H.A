package kr.happy.myarmy.Menu;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.Custom.LPEdittext;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.InfoAdapter;
import kr.happy.myarmy.Recyclerview.ItemResumenInfo;
import kr.happy.myarmy.UserDB.UserDBManager;

/**
 * Created by JY on 2017-04-15.
 */

public class InfoEditFragment extends Fragment {

    @Nullable    @BindView(R.id.rv_info)
    RecyclerView mRecyclerview;

    @Nullable     @BindString(R.string.name)
    String name;
    @Nullable     @BindString(R.string.birth)
    String birth;
    @Nullable    @BindString(R.string.wantjob)
    String wantJob;
    @Nullable    @BindString(R.string.specialnote)
    String specialNote;
    @Nullable    @BindString(R.string.certificate)
    String certificate;
    @Nullable    @BindString(R.string.education)
    String edu;
    @Nullable    @BindString(R.string.address)
    String address;
    @Nullable    @BindString(R.string.etccareer)
    String etcCareer;
    @Nullable    @BindString(R.string.phone)
    String phone;

    @Nullable @BindView(R.id.item_infoContent)
    LPEdittext input;

    private InfoAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ItemResumenInfo> dataSet;
    private String[] itemName; //항목 이름들
    private String[] itemContent;//입력한 항목 내용들
    private String[] columns; //데이터베이스 컬럼

    public UserDBManager mDBManager=null;

    public InfoEditFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infoedit, container, false);
        ButterKnife.bind(this, view);

        float x=view.getTop();
        float y=view.getY();

        setUserData(); //디비에서 데이터 받아와서 세팅

        mRecyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //세로로 뿌리기
        mLayoutManager.setSmoothScrollbarEnabled(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter = new InfoAdapter(getActivity(), dataSet, R.layout.item_info); //어댑터 등록
        adapter.setmItemContent(itemContent); //변경될 내용, 즉 정보를 담은 배열을 넘겨줌

        mRecyclerview.setAdapter(adapter);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());


        return view;
    }

    @Override
    public void onPause() { //여기서 데이터 작업할거.
        Log.d("jy", "onpause호출됨.");
        updateUserData();


        super.onPause();
    }

    /* update user data */
    public void updateUserData() {
        Log.d("jy", Arrays.toString(adapter.getmItemContent()));
        itemContent=adapter.getmItemContent();

        ContentValues contentValues = new ContentValues();

        for(int i=0; i< mRecyclerview.getAdapter().getItemCount(); i++){
            contentValues.put(columns[i + 1], itemContent[i]);
        }
        mDBManager.update(contentValues, "_id=? ", new String[]{"1"});
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBManager=UserDBManager.getInstance(getActivity()); //dbmanager
    }



    /*set data*/
    public void setUserData() {
        columns=new String[]{"id","name", "birth", "wantjob", "specialnote", "certificate","edu", "address", "etccareer", "phone"}; //받아올 컬럼들

        itemName = new String[]{name, birth, wantJob, specialNote, certificate, edu, address, etcCareer, phone}; //항목이름
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
            dataSet.add(new ItemResumenInfo(itemName[i], itemContent[i]));
        }
    }
}
