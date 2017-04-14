package kr.happy.myarmy.Menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.ItemHome;
import kr.happy.myarmy.Recyclerview.RecyclerAdapter;


/**
 * Created by JY on 2017-04-11.
 */

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerview;
    private RecyclerAdapter adapter;
    private StaggeredGridLayoutManager  mLayoutManager;
    private ArrayList<ItemHome> dataSet=new ArrayList<ItemHome>();

    public HomeFragment(){} //기본생성자

    @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ViewGroup view=(ViewGroup)inflater.inflate(R.layout.home, container, false);

        mRecyclerview=(RecyclerView) view.findViewById(R.id.rv_home);
        mRecyclerview.setHasFixedSize(true);

        mLayoutManager=new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS); //알아서 잘조정
        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter=new RecyclerAdapter(getActivity(), dataSet, R.layout.item_home); //어댑터 등록
        mRecyclerview.setAdapter(adapter);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for(int i=0; i<20; i++)
            dataSet.add(new ItemHome(R.drawable.daehantong, "한국공항공사", "2017년도 상반기 신입사원","(채용형인턴) 공개채용" , "D-5 | 경력무관 | 대졸"));

    }


}
