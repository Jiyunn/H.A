package me.happy.win3win.Menu;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.happy.win3win.R;
import me.happy.win3win.Recyclerview.GroupAdapter;
import me.happy.win3win.Server.Item;
import me.happy.win3win.Server.ReqItems;
import me.happy.win3win.Server.RetroInterface;
import me.happy.win3win.Server.ServerGenerator;
import me.happy.win3win.UserDB.UserDBManager;
import me.happy.win3win.databinding.RegiongroupBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JY on 2017-04-11.
 */

public class RegionGroupFragment extends Fragment {

    RegiongroupBinding binding;

    private GroupAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Item> dataSet;
    private String nowArea;
    private String token;

    private String[] url;

    FragmentManager fgManager;
    UserDBManager mDBManager;

    public RegionGroupFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.regiongroup, container, false);
        View view = binding.getRoot();
        binding.setRegiongroup(this);
        ButterKnife.bind(this, view);

        binding.rvRegion.setHasFixedSize(true);
        binding.rvRegion.setItemAnimator(new DefaultItemAnimator());
        adapter = new GroupAdapter(getActivity(), dataSet, R.layout.item_job, binding.rvRegion, fgManager);
        binding.rvRegion.setAdapter(adapter);
        /*
        지역별 데이터 가져오기
         */
        callRegionAPI(ServerGenerator.getRequestService(), nowArea);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvRegion.setLayoutManager(mLayoutManager);

        return view;
    }


    /* get data*/
    public void callRegionAPI(RetroInterface apiService, String nowArea) {
        Call<ReqItems> call = apiService.getAreaList(token, nowArea);


        if (nowArea.equals("서울") || nowArea.equals("경기")) ;
        {
            dataSet.clear();
            adapter.notifyDataSetChanged();
        }

        call.enqueue(new Callback<ReqItems>() {
            @Override
            public void onResponse(Call<ReqItems> call, Response<ReqItems> response) {
                if (response.isSuccessful()) {
                    dataSet.addAll(dataSet.size(), response.body().getRequestList());

                    for(int i=0; i< dataSet.size(); i++)
                        dataSet.get(i).setThumbnail(url[i% url.length]);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ReqItems> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /*
    지역 btn
     */
    @OnClick({R.id.region_gyeongi,  R.id.region_gangwon,  R.id.region_chungbook,  R.id.region_chungnam,R.id.region_kyeongbook,  R.id.region_kyeongnam,  R.id.region_jeonbook, R.id.region_jeonnam })
    public void onRegionClick(View v) {
        int btnId = v.getId();

        switch (btnId) {
            case R.id.region_gyeongi:
                callRegionAPI(ServerGenerator.getRequestService(), "경기도");
                callRegionAPI(ServerGenerator.getRequestService(), "서울특별시");
                break;
            case R.id.region_gangwon:
                callRegionAPI(ServerGenerator.getRequestService(), getString(R.string.gangwon));
                break;
            case R.id.region_chungbook:
                callRegionAPI(ServerGenerator.getRequestService(), getString(R.string.chungbook));
                break;
            case R.id.region_chungnam:
                callRegionAPI(ServerGenerator.getRequestService(), getString(R.string.chungnam));
                break;
            case R.id.region_kyeongbook:
                callRegionAPI(ServerGenerator.getRequestService(), getString(R.string.kyeongbook));
                break;
            case R.id.region_kyeongnam:
                callRegionAPI(ServerGenerator.getRequestService(), getString(R.string.kyeongnam));
                break;
            case R.id.region_jeonbook:
                callRegionAPI(ServerGenerator.getRequestService(), getString(R.string.jeonbook));
                break;
            case R.id.region_jeonnam:
                callRegionAPI(ServerGenerator.getRequestService(), getString(R.string.jeonnam));
                break;
        }
    }

    /*
    객체 생성 및 토큰 받아오기
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nowArea="경기도";

        fgManager = getFragmentManager();
        dataSet = new ArrayList<>();

        url = new String[]{
                "http://img.jobkorea.kr/trans/c/200x80/d/k/JK_Co_dkvascom1.png",
                "http://img.jobkorea.kr/trans/c/200x80/n/i/JK_Co_nit0100.png",
                "http://img.jobkorea.kr/trans/c/200x80/f/e/JK_Co_fe120808.png",
                "http://img.jobkorea.kr/trans/c/200x80/n/a/JK_Co_nava007.png",
                "http://img.jobkorea.kr/trans/c/200x80/a/c/JK_Co_acegluer.png",
                "http://img.jobkorea.kr/trans/c/200x80/d/k/JK_Co_dkr2011.png",
                "http://img.jobkorea.kr/trans/c/200x80/d/a/JK_Co_daeilfnb420.png",
                "http://img.jobkorea.kr/trans/c/200x80/a/s/JK_Co_asea2010.png",
                "http://img.jobkorea.kr/trans/c/200x80/w/n/JK_Co_wnwpdldostl.png",
                "http://img.jobkorea.kr/trans/c/200x80/g/o/JK_Co_goodwater.png",
                "http://img.jobkorea.kr/trans/c/200x80/q/w/JK_Co_qwedc23.png",
                "http://img.jobkorea.kr/trans/c/200x80/d/a/JK_Co_daeoco.png",
                "http://img.jobkorea.kr/trans/c/200x80/c/o/JK_Co_coset1647.png",
                "http://img.jobkorea.kr/trans/c/200x80/k/n/JK_Co_knlsystem.png"
                };

        mDBManager = UserDBManager.getInstance(getActivity());

        Cursor c = mDBManager.query(new String[]{"token"}, null, null, null, null, null);

        if (c != null && c.moveToFirst())
            token = c.getString(0);
        c.close();
    }

}
