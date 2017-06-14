package me.happy.win3win.Recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.happy.win3win.databinding.ItemMykeywordBinding;

/**
 * Created by JY on 2017-05-24.
 */

public class MySearchAdapter extends RecyclerView.Adapter<MySearchAdapter.MyViewHolder>  {

    private Context context;
    private ArrayList<TwoString> twodataSet;
    private int itemLayout;
    private RecyclerView recyclerView;

    public MySearchAdapter(Context context, ArrayList<TwoString> twodataSet, int itemLayout, RecyclerView recyclerView) {
        this.context = context;
        this.twodataSet = twodataSet;
        this.itemLayout = itemLayout;
        this.recyclerView = recyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);


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


    static class MyViewHolder extends RecyclerView.ViewHolder{

        ItemMykeywordBinding binding;


        public MyViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

    }
}
