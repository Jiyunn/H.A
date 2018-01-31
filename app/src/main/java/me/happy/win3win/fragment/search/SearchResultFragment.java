package me.happy.win3win.fragment.search;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.happy.win3win.R;
import me.happy.win3win.databinding.FragmentSearchResultBinding;
import me.happy.win3win.db.UserDBManager;
import me.happy.win3win.fragment.tab.adapter.HomeAdapter;
import me.happy.win3win.model.Gonggo;
import me.happy.win3win.model.ReqItems;
import me.happy.win3win.network.RetroInterface;
import me.happy.win3win.network.ServerGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JY on 2017-05-23.
 */

public class SearchResultFragment  extends Fragment implements View.OnFocusChangeListener{

    private FragmentSearchResultBinding binding;

    private List<Gonggo> dataSet;
    private HomeAdapter adapterSet;
    private StaggeredGridLayoutManager mLayoutManager;

    private String token;
    private UserDBManager mDBManager;
    private FragmentManager fgManager;

    private static String text;
    private String[] url;


    public SearchResultFragment() {    }

    public static SearchResultFragment newInstance(String  text) {

        Bundle args = new Bundle();
        args.putString("TEXT", text);

        SearchResultFragment fragment = new SearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_result, container, false);
        View view= binding.getRoot();

        binding.rvSResult.setHasFixedSize(true);
        binding.rvSResult.setItemAnimator(new DefaultItemAnimator());

        adapterSet = new HomeAdapter(getActivity(), dataSet, R.layout.item_home, binding.rvSResult , fgManager, R.id.sfrag);
        binding.rvSResult.setAdapter(adapterSet);

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS); //알아서 잘 조정
        binding.rvSResult.setLayoutManager(mLayoutManager);


        callSearchAPI(ServerGenerator.getRequestService());

        return view;
    }

    /*
    검색내용 서버에서 가져오기
  */
    public void callSearchAPI(RetroInterface apiService) {

        Call<ReqItems> call = apiService.getSearchResult(token, text);

        dataSet.clear();

        call.enqueue(new Callback<ReqItems>() {
            @Override
            public void onResponse(Call<ReqItems> call, Response<ReqItems> response) {
                if (response.isSuccessful()) {

                    dataSet.addAll(dataSet.size(), response.body().getRequestList());

                    for (int i = 0; i < dataSet.size(); i++)
                        dataSet.get(i).setThumbnail(url[i % url.length]);
                    adapterSet.notifyDataSetChanged();

                    SpannableStringBuilder ssb = new SpannableStringBuilder(String.valueOf(dataSet.size())+" 건의 검색결과를 발견했습니다");
                    ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#EC7063")), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    binding.srTv.setText(ssb);
                }
            }
            @Override
            public void onFailure(Call<ReqItems> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fgManager = getFragmentManager();
        mDBManager = UserDBManager.getInstance(getActivity());
        getTokenFromDB();

        if(getArguments() !=null)
            text = getArguments().getString("TEXT");

        Log.d("jy", "검색내용 : " +text);
        dataSet = new ArrayList<>();

        //로고 url
        url = new String[]{"http://img.jobkorea.kr/trans/c/200x80/c/o/JK_Co_coset1647.png",
                "http://img.jobkorea.kr/trans/c/200x80/k/n/JK_Co_knlsystem.png",
                "http://img.jobkorea.kr/trans/c/200x80/d/k/JK_Co_dkvascom1.png",
                "http://img.jobkorea.kr/trans/c/200x80/a/c/JK_Co_acegluer.png",
                "http://img.jobkorea.kr/trans/c/200x80/w/n/JK_Co_wnwpdldostl.png",
                "http://img.jobkorea.kr/trans/c/200x80/n/a/JK_Co_nava007.png"};
    }


    /*
 get token from db
  */
    public void getTokenFromDB() {
        Cursor c = mDBManager.query(new String[]{"token"}, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            token = c.getString(0);
        }
        c.close();
    }

    /*
    다시 이동?할까말까
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(v.getId() == R.id.toolbar_search){
            fgManager
                    .beginTransaction()
                    .replace(R.id.sfrag, new WhileSearchFragment())
                    .commit();
        }
    }
}
