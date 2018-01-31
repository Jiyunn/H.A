package me.happy.win3win.searchMenu;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.happy.win3win.R;
import me.happy.win3win.custom.SearchEdittext;
import me.happy.win3win.databinding.RecentsearchBinding;
import me.happy.win3win.network.Item;
import me.happy.win3win.network.ReqItems;
import me.happy.win3win.network.RetroInterface;
import me.happy.win3win.network.ServerGenerator;
import me.happy.win3win.recyclerview.PopSearchAdapter;
import me.happy.win3win.recyclerview.TwoString;
import me.happy.win3win.userdb.UserDBManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JY on 2017-05-23.
 */

public class WhileSearchingFragment extends Fragment implements TextWatcher, View.OnClickListener {

    private RecentsearchBinding binding; //초기화면 이걸로 붙임

    private PopSearchAdapter adapter;
    private List<Item> dataResult; //검색 결과
    private List<Item> dataSet;
    private List<TwoString> searchResult;
    private FragmentManager fgManager;

    private SearchEdittext searchEdittext;

    private String token;
    private String text;


    private UserDBManager mDBManager;

    private LinearLayoutManager mLayoutManager;

    public WhileSearchingFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.recentsearch, container, false);
        View view = binding.getRoot();
        binding.setRecentsearch(this);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvSearch.setLayoutManager(mLayoutManager);

        adapter = new PopSearchAdapter(getActivity(), searchResult, R.layout.item_search, fgManager, binding.rvSearch);
        binding.rvSearch.setAdapter(adapter);

        callAPI(ServerGenerator.getRequestService());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       searchEdittext = (SearchEdittext) (((SearchActivity) getContext()).findViewById(R.id.toolbar_search)); //바텀바 안보이게
        searchEdittext.addTextChangedListener(this);
        searchEdittext.setOnClickListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataResult = new ArrayList<>();
        dataSet = new ArrayList<>();

        fgManager = getFragmentManager();
        mDBManager = UserDBManager.getInstance(getActivity());

        Cursor c = mDBManager.query(new String[]{"token"}, null, null, null, null, null);

        if (c != null && c.moveToFirst()) {
            token = c.getString(0);
        }
        c.close();
    }

    /*
 전체항목 불러오기
 */
    public void callAPI(RetroInterface apiService) {
        Call<ReqItems> call = apiService.getHomeList(token);

        call.enqueue(new Callback<ReqItems>() {
            @Override
            public void onResponse(Call<ReqItems> call, Response<ReqItems> response) {
                if (response.isSuccessful()) {

                    dataSet.addAll(dataSet.size(), response.body().getRequestList());
                }
            }

            @Override
            public void onFailure(Call<ReqItems> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    /*
     리스트 검색기능
      */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String query = s.toString().trim();
        searchResult = new ArrayList<>();

        for (int i = 0; i < dataSet.size(); i++) { //검색한 항목이 존재하면 리스트에 추가
            if (dataSet.contains(query)) {
                searchResult.add(new TwoString(query, String.valueOf(dataSet.get(i).getId())));
                text =query;
            }
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void afterTextChanged(Editable s) {
    }

    /*
    검색 결과화면으로 이동
     */
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.toolbar_search){
            FragmentManager fgManager = getFragmentManager();
           SearchResultFragment searchResultFragment = new SearchResultFragment();

            fgManager
                    .beginTransaction()
                    .replace(R.id.sfrag, searchResultFragment.newInstance(text))
                    .commit();
        }
    }
}
