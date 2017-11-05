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
import me.happy.win3win.databinding.ItemMykeywordBinding;

/**
 * Created by JY on 2017-05-24.
 */

public class MySearchAdapter extends RecyclerView.Adapter<MySearchAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<TwoString> twodataSet;
    private int itemLayout;
    private RecyclerView mRecyclerView;
    private FragmentManager fgManager;

    public MySearchAdapter(Context context, ArrayList<TwoString> twodataSet, int itemLayout, FragmentManager fgManager, RecyclerView mRecyclerView) {
        this.context = context;
        this.twodataSet = twodataSet;
        this.itemLayout = itemLayout;
        this.mRecyclerView = mRecyclerView;
        this.fgManager = fgManager;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        itemView.setOnClickListener(this);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TwoString twoString = twodataSet.get(position);
        holder.binding.setTwodata(twoString);
    }


    @Override
    public int getItemCount() {
        return (twodataSet != null) ? twodataSet.size() : 0;
    }

    /*
    검색어 항목의 x표시를 클릭하면 지우기, 그냥 항목이름을 클릭하면 그 검색어로 검색하기
     */
    @Override
    public void onClick(View v) {
        int curPos = mRecyclerView.getChildAdapterPosition(v); //클릭된 차일드의 현재 포지션

        if (v.getId() == R.id.item_myDel)
            this.twodataSet.remove(curPos);

        else {
            SearchResultFragment searchResultFragment = new SearchResultFragment();

            fgManager
                    .beginTransaction()
                    .replace(R.id.sfrag, searchResultFragment.newInstance(twodataSet.get(curPos).getContent()))
                    .commit();
        }

    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        ItemMykeywordBinding binding;


        public MyViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

    }
}
