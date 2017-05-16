package kr.happy.myarmy.Menu;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
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

import java.util.ArrayList;

import kr.happy.myarmy.Custom.EndlessRecyclerViewScrollListener;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.GroupAdapter;
import kr.happy.myarmy.Server.Item;
import kr.happy.myarmy.Server.Items;
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
        View view=binding.getRoot();
        binding.setJobgroup(this);


        binding.rvJob.setHasFixedSize(true);
        binding.rvJob.setItemAnimator(new DefaultItemAnimator());
        adapter = new GroupAdapter(getActivity(), dataSet, R.layout.item_job,  fgManager);
        binding.rvJob.setAdapter(adapter);

        callJobAPI(ServerGenerator.getRequestService(), nowJob); //API불러오기 시작

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); //세로로 뿌리기
        binding.rvJob.setLayoutManager(mLayoutManager);

        /*more load data*/
            binding.rvJob.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager, 5) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                callJobAPI(ServerGenerator.getAPIService(), nowJob) ;
            }
        });


        return view;
    }

    /* get data*/
    public void callJobAPI(RetroInterface apiService, String job) {
        Call<Items> call= apiService.getJobList(token, job);

        call.enqueue(new Callback<Items>() {
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                if (response.isSuccessful()) {
//                    dataSet.addAll(dataSet.size(), response.body().getResultList());
//                    Log.d("jy", "성공?0");

                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {
                if (t.getMessage() != null)
                    Log.d("jy", "call API on Failure.. : " + t.getMessage());
            }
        });
    }

    /* for jobGroup btn*/
    public void onJobClick(View v) {
        int btnId = v.getId();

        switch (btnId) {
            case R.id.job_gigye :
                callJobAPI(ServerGenerator.getRequestService(), "기계");
                break;
            case R.id.job_jeonja :
                callJobAPI(ServerGenerator.getRequestService(), "전자");
                break;
            case R.id.job_jeongi :
                callJobAPI(ServerGenerator.getRequestService(), "전기");
                break;
            case R.id.job_saengsan :
                callJobAPI(ServerGenerator.getRequestService(), "생산");
                break;
            case R.id.job_euiyak :
                callJobAPI(ServerGenerator.getRequestService(), "의료의약");
                break;
            case R.id.job_it :
                callJobAPI(ServerGenerator.getRequestService(), "정보처리");
                break;
            case R.id.job_gaebal :
                callJobAPI(ServerGenerator.getRequestService(), "연구");
                break;
            case R.id.job_whahak :
                callJobAPI(ServerGenerator.getRequestService(), "화학");
                break;
            case R.id.job_cheolgang:
                callJobAPI(ServerGenerator.getRequestService(), "철강");
                break;
            case R.id.job_euiryu :
                callJobAPI(ServerGenerator.getRequestService(), "의류");
                break;
            case R.id.job_somyou :
                callJobAPI(ServerGenerator.getRequestService(), "섬유");
                break;

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nowJob="기계";
        fgManager = getFragmentManager();
        dataSet = new ArrayList<>();
        mDBManager=UserDBManager.getInstance(getActivity());

        Cursor c=mDBManager.query(new String[]{"token"}, null,null,null,null,null);

        if(c !=null && c.moveToFirst())
            token=c.getString(0);
        c.close();
    }


}
