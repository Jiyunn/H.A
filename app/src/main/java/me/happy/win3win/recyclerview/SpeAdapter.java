package me.happy.win3win.recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.happy.win3win.databinding.ItemSpeBinding;

/**
 * Created by JY on 2017-05-15.
 */

public class SpeAdapter extends RecyclerView.Adapter<SpeAdapter.SpeViewHolder>{

    private Context context;
    private int itemLayout;
    private ArrayList<TwoString> twoStringSet;

    public SpeAdapter(Context context, ArrayList<TwoString> twoStringSet, int itemLayout) {
        this.context = context;
        this.twoStringSet = twoStringSet;
        this.itemLayout=itemLayout;
    }


    @Override
    public SpeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new SpeViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(SpeViewHolder holder, int position) {
        TwoString twoString = twoStringSet.get(position);
        holder.binding.setTwodata(twoString);

    }


    @Override
    public int getItemCount() {
        return (twoStringSet !=null) ? twoStringSet.size() : 0;
    }

    static class SpeViewHolder extends RecyclerView.ViewHolder{

        ItemSpeBinding binding;



        private SpeViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);

        }
    }
}
