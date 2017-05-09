package kr.happy.myarmy.Menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.HomeAdapter;
import kr.happy.myarmy.Retrofit2.Item;
import kr.happy.myarmy.Retrofit2.RetroInterface;
import kr.happy.myarmy.Retrofit2.ServerGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by JY on 2017-04-11.
 */

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Nullable @BindView(R.id.rv_home) RecyclerView mRecyclerview;
    @BindView(R.id.swipeLayout)SwipeRefreshLayout swipeLayout;

    private HomeAdapter adapter;
    private StaggeredGridLayoutManager  mLayoutManager;
    private ArrayList<Item> dataSet;

    static final String KEY = "service key";
    int curPage=1;
    int totalCnt=0;

    public HomeFragment(){} //기본생성자

    @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home, container, false);
        ButterKnife.bind(this, view);

        swipeLayout.setColorSchemeResources(R.color.navy_a);
        swipeLayout.setOnRefreshListener(this);

        mRecyclerview.setHasFixedSize(true);

        mLayoutManager=new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS); //알아서 잘조정
        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter=new HomeAdapter(getActivity(), dataSet, R.layout.item_home); //어댑터 등록
        mRecyclerview.setAdapter(adapter);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        callAPI(ServerGenerator.getAPIService()); //API불러오기 시작


        return view;
    }

    /* get data*/
    public void callAPI(RetroInterface apiService){

        Call<Chaeyong> call=apiService.getList(20, curPage++,"json", KEY ); //인터페이스의 getList메소드를 통해, 원하는 데이터 가져오기.

        call.enqueue(new Callback<Chaeyong>() { //콜백으로 받아오기
            @Override
            public void onResponse(Call<Chaeyong> call, Response<Chaeyong> response) {
                if (response.isSuccessful()) { //성공했을 경우,

                    dataSet.addAll(dataSet.size(), response.body().getResponse().getBody().getItems().getItemList()); //items의 항목들을 받아온다.
                    totalCnt = response.body().getResponse().getBody().getTotalCount();
                    curPage = response.body().getResponse().getBody().getPageNo();

                    adapter.setGongos(dataSet);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Chaeyong> call, Throwable t) {
                if(t.getMessage() !=null)
                    Log.d("jy", "call API on Failure.. : " +t.getMessage());
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataSet= new ArrayList<>();
    }

    @Override
    public void onRefresh() {
         dataSet.clear();
        adapter.notifyDataSetChanged();
        callAPI(ServerGenerator.getAPIService());
        swipeLayout.setRefreshing(false);
    }


}
