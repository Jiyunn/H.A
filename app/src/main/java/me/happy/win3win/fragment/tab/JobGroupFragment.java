package me.happy.win3win.fragment.tab;

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
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.happy.win3win.R;
import me.happy.win3win.databinding.FragmentJobgroupBinding;
import me.happy.win3win.db.UserDBManager;
import me.happy.win3win.fragment.tab.adapter.GroupAdapter;
import me.happy.win3win.model.Gonggo;
import me.happy.win3win.model.ReqItems;
import me.happy.win3win.network.RetroInterface;
import me.happy.win3win.network.ServerGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JY on 2017-04-11.
 */

public class JobGroupFragment extends Fragment {

    private FragmentJobgroupBinding binding;

    private GroupAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private List<Gonggo> dataSet;
    private String token;
    private String nowJob;

    private String[] url;

    FragmentManager fgManager;
    UserDBManager mDBManager;

    public JobGroupFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_jobgroup, container, false);
        View view = binding.getRoot();
        binding.setFragment(this);
        ButterKnife.bind(this, view);

        binding.rvJob.setHasFixedSize(true);
        binding.rvJob.setItemAnimator(new DefaultItemAnimator());
        adapter = new GroupAdapter(getActivity(), dataSet, R.layout.item_job, binding.rvJob, fgManager);
        binding.rvJob.setAdapter(adapter);
        /*
        직군별 데이터 가져오기
         */
        callJobAPI(ServerGenerator.getRequestService(), nowJob);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvJob.setLayoutManager(mLayoutManager);

        return view;
    }

    /* get data*/
    public void callJobAPI(RetroInterface apiService, String job) {
        Call<ReqItems> call = apiService.getJobList(token, job);

        dataSet.clear();

        call.enqueue(new Callback<ReqItems>() {
            @Override
            public void onResponse(Call<ReqItems> call, Response<ReqItems> response) {
                if (response.isSuccessful()) {
                    dataSet.addAll(dataSet.size(), response.body().getRequestList());

                    for(int i=0; i< dataSet.size(); i++) {
                        dataSet.get(i).setThumbnail(url[i % url.length]);

                    }adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ReqItems> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /*
     직군 btn
     */
    @OnClick({R.id.job_gigye, R.id.job_jeonja, R.id.job_jeongi, R.id.job_saengsan, R.id.job_euiyak, R.id.job_it, R.id.job_gaebal, R.id.job_whahak, R.id.job_cheolgang, R.id.job_euiryu, R.id.job_somyou})
    public void onJobClick(View v) {
        int btnId = v.getId();

        switch (btnId) {
            case R.id.job_gigye:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.gigye));
                break;
            case R.id.job_jeonja:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.jeonja));
                break;
            case R.id.job_jeongi:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.jeongi));
                break;
            case R.id.job_saengsan:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.saengsan));
                break;
            case R.id.job_euiyak:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.euiyak));
                break;
            case R.id.job_it:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.it));
                break;
            case R.id.job_gaebal:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.gaebal));
                break;
            case R.id.job_whahak:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.whahak));
                break;
            case R.id.job_cheolgang:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.cheolgang));
                break;
            case R.id.job_euiryu:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.euiryu));
                break;
            case R.id.job_somyou:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.somyou));
                break;

        }
    }

    /*
        객체 생성 및 토큰 받아오기
         */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nowJob = "기계";

        fgManager = getFragmentManager();
        dataSet = new ArrayList<>();

        url = new String[]{
                "http://img.jobkorea.kr/trans/c/200x80/a/c/JK_Co_acegluer.png",
                "http://img.jobkorea.kr/trans/c/200x80/d/k/JK_Co_dkr2011.png",
                "http://img.jobkorea.kr/trans/c/200x80/d/a/JK_Co_daeilfnb420.png",
              };


        mDBManager = UserDBManager.getInstance(getActivity());

        Cursor c = mDBManager.query(new String[]{"token"}, null, null, null, null, null);

        if (c != null && c.moveToFirst())
            token = c.getString(0);
        c.close();
    }


}
