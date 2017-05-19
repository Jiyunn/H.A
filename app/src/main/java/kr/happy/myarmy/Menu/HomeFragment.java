package kr.happy.myarmy.Menu;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.HomeAdapter;
import kr.happy.myarmy.Server.Item;
import kr.happy.myarmy.Server.ReqItems;
import kr.happy.myarmy.Server.RetroInterface;
import kr.happy.myarmy.Server.ServerGenerator;
import kr.happy.myarmy.UserDB.UserDBManager;
import kr.happy.myarmy.databinding.HomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by JY on 2017-04-11.
 */

public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<Item> dataSet;
    private HomeAdapter adapter;
    private StaggeredGridLayoutManager mLayoutManager;
    private String token;

    UserDBManager mDBManager;
    FragmentManager fgManager;
    HomeBinding binding;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.home, container, false);
        View view = binding.getRoot();

        binding.swipeLayout.setColorSchemeResources(R.color.orange_a);
        binding.swipeLayout.setOnRefreshListener(this);

       binding.rvHome.setHasFixedSize(true);

        adapter = new HomeAdapter(getActivity(), dataSet, R.layout.item_home,   binding.rvHome, fgManager); //어댑터 등록
        binding.rvHome.setAdapter(adapter);

        callAPI(ServerGenerator.getRequestService());

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS); //알아서 잘 조정
        binding.rvHome.setLayoutManager(mLayoutManager);


        binding.rvHome.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    /* get data*/
    public void callAPI(RetroInterface apiService) {
        Call<ReqItems> call = apiService.getHomeList(token);

        dataSet.clear();
        adapter.notifyDataSetChanged();

        call.enqueue(new Callback<ReqItems>() {
            @Override
            public void onResponse(Call<ReqItems> call, Response<ReqItems> response) {
                if (response.isSuccessful()) {
                    try {
                        dataSet.addAll(dataSet.size(), response.body().getRequestList());
                        adapter.notifyDataSetChanged();

                    }catch(NumberFormatException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReqItems> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDBManager=UserDBManager.getInstance(getActivity());
        fgManager = getFragmentManager();
        dataSet = new ArrayList<>();

        Cursor c=mDBManager.query(new String[]{"token"}, null, null, null, null, null);

        if(c!=null && c.moveToFirst())
            token=c.getString(0);
        c.close();
    }

    /*refresh scroll*/
    @Override
    public void onRefresh() {
        callAPI(ServerGenerator.getRequestService());
        binding.swipeLayout.setRefreshing(false);
    }

}
