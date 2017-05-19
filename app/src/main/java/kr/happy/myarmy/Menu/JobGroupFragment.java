package kr.happy.myarmy.Menu;

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

import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.GroupAdapter;
import kr.happy.myarmy.Server.Item;
import kr.happy.myarmy.Server.ReqItems;
import kr.happy.myarmy.Server.RetroInterface;
import kr.happy.myarmy.Server.ServerGenerator;
import kr.happy.myarmy.UserDB.UserDBManager;
import kr.happy.myarmy.databinding.JobgroupBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JY on 2017-04-11.
 */

public class JobGroupFragment extends Fragment {

    private JobgroupBinding binding;

    private GroupAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Item> dataSet;
    private String token;
    private String nowJob;

    FragmentManager fgManager;
    UserDBManager mDBManager;

    public JobGroupFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.jobgroup, container, false);
        View view = binding.getRoot();
        binding.setJobgroup(this);

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
        adapter.notifyDataSetChanged();


        call.enqueue(new Callback<ReqItems>() {
            @Override
            public void onResponse(Call<ReqItems> call, Response<ReqItems> response) {
                if (response.isSuccessful()) {
                    dataSet.addAll(dataSet.size(), response.body().getRequestList());
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
     직군 btn
     */
    public void onJobClick(View v) {
        int btnId = v.getId();

        switch (btnId) {
            case R.id.job_gigye:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.gigye));
                break;
            case R.id.job_jeonja:
                callJobAPI(ServerGenerator.getRequestService(), "전자");
                break;
            case R.id.job_jeongi:
                callJobAPI(ServerGenerator.getRequestService(), "전기");
                break;
            case R.id.job_saengsan:
                callJobAPI(ServerGenerator.getRequestService(), getString(R.string.saengsan));
                break;
            case R.id.job_euiyak:
                callJobAPI(ServerGenerator.getRequestService(), "의료의약");
                break;
            case R.id.job_it:
                callJobAPI(ServerGenerator.getRequestService(), "정보처리");
                break;
            case R.id.job_gaebal:
                callJobAPI(ServerGenerator.getRequestService(), "연구");
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

        mDBManager = UserDBManager.getInstance(getActivity());

        Cursor c = mDBManager.query(new String[]{"token"}, null, null, null, null, null);

        if (c != null && c.moveToFirst())
            token = c.getString(0);
        c.close();
    }


}
