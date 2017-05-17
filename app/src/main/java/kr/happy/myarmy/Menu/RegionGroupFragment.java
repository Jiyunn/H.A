package kr.happy.myarmy.Menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.GroupAdapter;
import kr.happy.myarmy.Server.Item;
import kr.happy.myarmy.Server.RetroInterface;
import kr.happy.myarmy.Server.ServerGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JY on 2017-04-11.
 */

public class RegionGroupFragment extends Fragment {

    @Nullable @BindView(R.id.rv_region)
    RecyclerView mRecyclerview;

    @Nullable @BindView(R.id.item_jobFavorite)
    ImageButton favorite;

    private GroupAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Item> dataSet;

    static final String KEY = "";
    int curPage = 1;
    int totalCnt = 0;

    FragmentManager fgManager;

    public RegionGroupFragment(){} //기본생성자

    @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.regiongroup, container, false);
        ButterKnife.bind(this, view);

        mRecyclerview.setHasFixedSize(true);

//        adapter = new GroupAdapter(getActivity(), dataSet, R.layout.item_job, fgManager); //어댑터 등록
        mRecyclerview.setAdapter(adapter);

        callAPI(ServerGenerator.getAPIService()); //API불러오기 시작

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //세로로 뿌리기
        mRecyclerview.setLayoutManager(mLayoutManager);

        mRecyclerview.setItemAnimator(new DefaultItemAnimator());


        return view;
    }


    /* get data*/
    public void callAPI(RetroInterface apiService) {

        Call<Chaeyong> call = apiService.getList(20, curPage++, "json", KEY); //인터페이스의 getList메소드를 통해, 원하는 데이터 가져오기.

        call.enqueue(new Callback<Chaeyong>() {
            @Override
            public void onResponse(Call<Chaeyong> call, Response<Chaeyong> response) {
                if (response.isSuccessful()) {
                    dataSet.addAll(dataSet.size(), response.body().getResponse().getBody().getItems().getItemList()); //items의 항목들을 받아온다.
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


}
