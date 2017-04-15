package kr.happy.myarmy.Menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.happy.myarmy.InfoEditFragment;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.InfoAdapter;
import kr.happy.myarmy.Recyclerview.ItemResume;

/**
 * Created by JY on 2017-04-11.
 */

public class MyResumeFragment extends android.support.v4.app.Fragment {

    @Nullable @BindView(R.id.rv_myresume)  RecyclerView mRecyclerview;

    @Nullable @BindString(R.string.wantjob)  String wantJob;
    @Nullable @BindString(R.string.specialnote)  String specialNote;
    @Nullable @BindString(R.string.certificate)  String certificate;
    @Nullable @BindString(R.string.education)  String edu;
    @Nullable @BindString(R.string.living)  String living;
    @Nullable @BindString(R.string.etccareer) String etcCareer;

    private InfoAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ItemResume> dataSet;
    private String[] itemName; //항목 이름들

    public MyResumeFragment() {
    } //기본생성자

    @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.myresume, container, false);
        ButterKnife.bind(this, view);

        setData(); //데이터 설정

        mRecyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //세로로 뿌리기
        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter = new InfoAdapter(getActivity(), dataSet, R.layout.item_myresume, itemName.length); //어댑터 등록
        mRecyclerview.setAdapter(adapter);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    /*temp data */
    public void setData() {
        dataSet = new ArrayList<ItemResume>();
        itemName = new String[]{wantJob, specialNote, certificate, edu, living, etcCareer};

        for (int i = 0; i < itemName.length; i++) { //임시 실험데이터
            dataSet.add(new ItemResume(itemName[i], "엔지니어링"));
        }
    }

    /*when click profile edit btn*/
    @OnClick(R.id.btn_profileEdit)
    public void profileEdit() {

        FragmentManager fgManager = getFragmentManager();
        FragmentTransaction fgTransaction = fgManager.beginTransaction();
        fgTransaction.replace(R.id.frag, new InfoEditFragment()); //정보입력창으로 이동.
        fgTransaction.commit();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        Log.d("jy", "myresume destory");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("jy", "myresume detach");
        super.onDetach();
    }
}
