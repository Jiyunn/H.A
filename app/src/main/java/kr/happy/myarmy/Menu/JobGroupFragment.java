package kr.happy.myarmy.Menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.ItemHomenJob;
import kr.happy.myarmy.Recyclerview.JobAdapter;

/**
 * Created by JY on 2017-04-11.
 */

public class JobGroupFragment extends Fragment {

    @Nullable @BindView(R.id.rv_job)
    RecyclerView mRecyclerview;

    @Nullable @BindView(R.id.item_favorite)
    ImageButton favorite;

    private JobAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ItemHomenJob> dataSet;
    private String[] itemName; //항목 이름들

    public JobGroupFragment(){} //기본생성자

    @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.jobgroup, container, false);
        ButterKnife.bind(this, view);

        setData();

        mRecyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //세로로 뿌리기
        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter = new JobAdapter(getActivity(), dataSet, R.layout.item_job); //어댑터 등록
        mRecyclerview.setAdapter(adapter);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    /*temp data */
    public void setData() {
        dataSet = new ArrayList<ItemHomenJob>();

        for (int i = 0; i < 20; i++) { //임시 실험데이터
            dataSet.add(new ItemHomenJob(R.drawable.daehantong, "(주)한국공항공사", "2017년 하반기 신입사원", "(채용형인턴) 비공개채용", "D-7 | 경력유관 | 고졸"));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}
