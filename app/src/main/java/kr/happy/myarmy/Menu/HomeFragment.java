package kr.happy.myarmy.Menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import kr.happy.myarmy.Custom.EndlessRecyclerViewScrollListener;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.HomeAdapter;
import kr.happy.myarmy.Server.Item;
import kr.happy.myarmy.Server.RetroInterface;
import kr.happy.myarmy.Server.ServerGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by JY on 2017-04-11.
 */

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Nullable
    @BindView(R.id.rv_home)
    RecyclerView mRecyclerview;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    private ArrayList<Item> dataSet;
    private HomeAdapter adapter;
    private StaggeredGridLayoutManager mLayoutManager;
    private EndlessRecyclerViewScrollListener mScrollListener;

    FragmentManager fgManager;

    static final String KEY = "";
    int curPage = 1;
    int totalCnt = 0;

    public HomeFragment() {
    } //기본생성자


    @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);
        ButterKnife.bind(this, view);

        swipeLayout.setColorSchemeResources(R.color.orange_a);
        swipeLayout.setOnRefreshListener(this);

        mRecyclerview.setHasFixedSize(true);

        adapter = new HomeAdapter(getActivity(), dataSet, R.layout.item_home, mRecyclerview, fgManager); //어댑터 등록
        mRecyclerview.setAdapter(adapter);

        callAPI(ServerGenerator.getAPIService());

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS); //알아서 잘조정
        mRecyclerview.setLayoutManager(mLayoutManager);

        mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager,10) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                callAPI(ServerGenerator.getAPIService());
            }
        };
        mRecyclerview.addOnScrollListener(mScrollListener);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    /* call API and get data*/
    public void callAPI(RetroInterface apiService) {

        Call<Chaeyong> call = apiService.getList(20, curPage++, "json", KEY);

        call.enqueue(new Callback<Chaeyong>() {
            @Override
            public void onResponse(Call<Chaeyong> call, Response<Chaeyong> response) {
                if (response.isSuccessful()) {
                    dataSet.addAll(dataSet.size(), response.body().getResponse().getBody().getItems().getItemList());
                    totalCnt = response.body().getResponse().getBody().getTotalCount();

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Chaeyong> call, Throwable t) {
                if (t.getMessage() != null)
                    Log.d("jy", "call API on Failure.. : " + t.getMessage());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fgManager = getFragmentManager();
        dataSet = new ArrayList<>();
    }

    /*refresh scroll*/
    @Override
    public void onRefresh() {
        curPage = 1;
        dataSet.clear();
        adapter.notifyDataSetChanged();
        mScrollListener.resetState();
        callAPI(ServerGenerator.getAPIService());
        swipeLayout.setRefreshing(false);
    }

}
