package kr.happy.myarmy.Menu;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
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
    private ArrayList<Item> dataRec; //추천용 뷰
    private HomeAdapter adapterSet;
    private StaggeredGridLayoutManager mLayoutManager;
    private String token;

    UserDBManager mDBManager;
    FragmentManager fgManager;
    HomeBinding binding;

    private String[] url;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.home, container, false);
        View view = binding.getRoot();

        binding.swipeLayout.setColorSchemeResources(R.color.orange_a);
        binding.swipeLayout.setOnRefreshListener(this);

        initRecyclerview(binding.rvHome);
        callAPI(ServerGenerator.getRequestService());


        return view;
    }

    /* get data*/
    public void callAPI(RetroInterface apiService) {
        Call<ReqItems> call = apiService.getHomeList(token);

        dataSet.clear();
        dataRec.clear();

        call.enqueue(new Callback<ReqItems>() {
            @Override
            public void onResponse(Call<ReqItems> call, Response<ReqItems> response) {
                if (response.isSuccessful()) {

                    dataSet.addAll(dataSet.size(), response.body().getRequestList());

                    for (int i = 0; i < dataSet.size(); i++)
                        dataSet.get(i).setThumbnail(url[i % url.length]);

                    adapterSet.notifyDataSetChanged();

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

        mDBManager = UserDBManager.getInstance(getActivity());
        getTokenFromDB();

        fgManager = getFragmentManager();
        dataSet = new ArrayList<>();
        dataRec = new ArrayList<>();


        url = new String[]{"http://img.jobkorea.kr/trans/c/200x80/c/o/JK_Co_coset1647.png",
                "http://img.jobkorea.kr/trans/c/200x80/k/n/JK_Co_knlsystem.png",
                "http://img.jobkorea.kr/trans/c/200x80/d/k/JK_Co_dkvascom1.png",
                "http://img.jobkorea.kr/trans/c/200x80/a/c/JK_Co_acegluer.png",
                "http://img.jobkorea.kr/trans/c/200x80/w/n/JK_Co_wnwpdldostl.png",
                "http://img.jobkorea.kr/trans/c/200x80/n/a/JK_Co_nava007.png"};
    }

    /*refresh scroll*/
    @Override
    public void onRefresh() {
        callAPI(ServerGenerator.getRequestService());
        binding.swipeLayout.setRefreshing(false);
    }

    /*
 Recyclerview 초기화
  */
    protected void initRecyclerview(RecyclerView rv) {
        rv.setHasFixedSize(true);
        rv.setItemAnimator(new DefaultItemAnimator());


        if(rv.getId() == R.id.rv_home) {
            adapterSet = new HomeAdapter(getActivity(), dataSet, R.layout.item_home, rv, fgManager);
            rv.setAdapter(adapterSet);
        }

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS); //알아서 잘 조정
        rv.setLayoutManager(mLayoutManager);
    }

    /*
    get token from db
     */
    public void getTokenFromDB() {
        Cursor c = mDBManager.query(new String[]{"token", "email"}, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            token = c.getString(0);
            Log.d("jy", c.getString(1));
        }
        c.close();

    }
}
