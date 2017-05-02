package kr.happy.myarmy.Menu;

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

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.InfoAdapter;
import kr.happy.myarmy.Recyclerview.ItemResumenInfo;

/**
 * Created by JY on 2017-04-15.
 */

public class InfoEditFragment extends Fragment {

    @Nullable
    @BindView(R.id.rv_info)
    RecyclerView mRecyclerview;

    @Nullable
    @BindString(R.string.name)
    String name;
    @Nullable
    @BindString(R.string.birth)
    String birth;
    @Nullable
    @BindString(R.string.wantjob)
    String wantJob;
    @Nullable
    @BindString(R.string.specialnote)
    String specialNote;
    @Nullable
    @BindString(R.string.certificate)
    String certificate;
    @Nullable
    @BindString(R.string.education)
    String edu;
    @Nullable
    @BindString(R.string.address)
    String living;
    @Nullable
    @BindString(R.string.etccareer)
    String etcCareer;
    @Nullable
    @BindString(R.string.phone)
    String phone;

    private InfoAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ItemResumenInfo> dataSet;
    private String[] itemName; //항목 이름들
    private String[] itemContent;//입력한 항목 내용들

    public InfoEditFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infoedit, container, false);
        ButterKnife.bind(this, view);

        setData();

        mRecyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //세로로 뿌리기
        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter = new InfoAdapter(getActivity(), dataSet, R.layout.item_info); //어댑터 등록
        mRecyclerview.setAdapter(adapter);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("jy", "onaccreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() { //여기서 데이터 작업할거.
        Log.d("jy", "infoedit pause");
        super.onPause();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onDestroy() {
        Log.d("jy", "infoedit destory");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("jy", "infoedit detach");
        super.onDetach();
    }

    /*set data*/
    public void setData() {

        dataSet = new ArrayList<ItemResumenInfo>();
        itemName = new String[]{name, birth, wantJob, specialNote, certificate, edu, living, etcCareer, phone}; //항목이름

        for (int i = 0; i < itemName.length; i++) { //임시 실험데이터
            dataSet.add(new ItemResumenInfo(itemName[i], String.valueOf(i)));
        }
    }


}
