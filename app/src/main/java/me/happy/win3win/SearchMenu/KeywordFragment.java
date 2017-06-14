package me.happy.win3win.SearchMenu;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.happy.win3win.R;
import me.happy.win3win.SpeSearch.KeywordPagerAdapter;
import me.happy.win3win.databinding.WhilesearchingBinding;

/**
 * Created by JY on 2017-05-23.
 */

public class KeywordFragment extends Fragment  {

    WhilesearchingBinding binding;

    public KeywordFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.whilesearching, container, false);
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
