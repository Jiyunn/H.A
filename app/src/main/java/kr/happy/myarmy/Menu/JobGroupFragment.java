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

import java.util.ArrayList;

import kr.happy.myarmy.R;
import kr.happy.myarmy.RecyclerviewHome.ItemHome;
import kr.happy.myarmy.RecyclerviewHome.RecyclerAdapter;

/**
 * Created by JY on 2017-04-11.
 */

public class JobGroupFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private RecyclerAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<ItemHome> dataSet=new ArrayList<ItemHome>();

    public JobGroupFragment(){} //기본생성자

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view=(ViewGroup)inflater.inflate(R.layout.home, container, false);

        mRecyclerview=(RecyclerView) view.findViewById(R.id.rv_home);
        mRecyclerview.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter=new RecyclerAdapter(getActivity(), dataSet, R.layout.item_job); //어댑터 등록
        mRecyclerview.setAdapter(adapter);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
