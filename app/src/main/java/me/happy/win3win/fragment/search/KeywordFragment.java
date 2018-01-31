package me.happy.win3win.fragment.search;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.happy.win3win.R;
import me.happy.win3win.databinding.FragmentKeywordBinding;
import me.happy.win3win.fragment.search.adapter.KeywordPagerAdapter;

/**
 * Created by JY on 2017-05-23.
 */

public class KeywordFragment extends Fragment  {

    FragmentKeywordBinding binding; //검색하는 화면

    public KeywordFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_keyword, container, false);
        View view = binding.getRoot();

        binding.searchVp.setAdapter(new KeywordPagerAdapter(getFragmentManager()));
        binding.searchTab.setupWithViewPager(binding.searchVp);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
