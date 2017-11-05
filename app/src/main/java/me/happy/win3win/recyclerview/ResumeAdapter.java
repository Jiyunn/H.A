package me.happy.win3win.recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.happy.win3win.databinding.ItemMyresumeBinding;

/**
 * Created by JY on 2017-04-15.
 */

/*myresume recyclerview adapter*/
public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder> {

    private Context context;
    private ArrayList<TwoString> twoStringSet;
    private int itemLayout;


    public ResumeAdapter(Context context, ArrayList<TwoString> twoStringSet, int itemLayout) {
        this.context = context;
        this.twoStringSet = twoStringSet;
        this.itemLayout = itemLayout;
    }

    @Override
    public ResumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ResumeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResumeViewHolder holder, int position) {
        TwoString twoString = twoStringSet.get(position);
        holder.binding.setTwodata(twoString);

    }

    @Override
    public int getItemCount() {
        return (twoStringSet != null) ? twoStringSet.size() : 0;
    }

    public void setItems(ArrayList<TwoString> items) {
        this.twoStringSet = items;
    }


    /*ResumeViewHolder class*/
    static class ResumeViewHolder extends RecyclerView.ViewHolder {

        ItemMyresumeBinding binding;


        private ResumeViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
