package me.happy.win3win.recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.happy.win3win.R;
import me.happy.win3win.searchMenu.SearchResultFragment;
import me.happy.win3win.databinding.ItemPopkeywordBinding;

/**
 * Created by JY on 2017-05-23.
 */

public class PopSearchAdapter extends RecyclerView.Adapter<PopSearchAdapter.SearchViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<TwoString> twodataSet;
    private int itemLayout;
    private FragmentManager fgManager;
    private RecyclerView mRecyclerView;

    public PopSearchAdapter(Context context, ArrayList<TwoString> twodataSet, int itemLayout, FragmentManager fgManager, RecyclerView mRecyclerView) {
        this.context = context;
        this.twodataSet = twodataSet;
        this.itemLayout = itemLayout;
        this.fgManager = fgManager;
        this.mRecyclerView =mRecyclerView;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        itemView.setOnClickListener(this);

        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        TwoString twoString = twodataSet.get(position);
        holder.binding.setTwodata(twoString);

        if( position >3)  //3위안에 드는 검색어만 색상 변경
            holder.binding.itemPopNum.setBackgroundResource(R.color.nineb);
    }

    @Override
    public int getItemCount() {
        return (twodataSet != null) ? twodataSet.size() : 0;
    }

    @Override
    public void onClick(View v) {
        int curPos = mRecyclerView.getChildAdapterPosition(v);

        SearchResultFragment searchResultFragment = new SearchResultFragment();

        fgManager
                .beginTransaction()
                .replace(R.id.sfrag, searchResultFragment.newInstance(twodataSet.get(curPos).getContent()))
                .commit();
    }


    static class SearchViewHolder extends RecyclerView.ViewHolder {

        ItemPopkeywordBinding binding;


        public SearchViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
