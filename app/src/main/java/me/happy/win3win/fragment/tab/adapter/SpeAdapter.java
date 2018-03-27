package me.happy.win3win.fragment.tab.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.happy.win3win.databinding.ItemSpeBinding;
import me.happy.win3win.fragment.tab.model.Keyword;

/**
 * Created by JY on 2017-05-15.
 */

public class SpeAdapter extends RecyclerView.Adapter<SpeAdapter.SpeViewHolder>{

    private Context context;
    private int itemLayout;
    private List<Keyword> keywordSet;

    public SpeAdapter(Context context, List<Keyword> keywordSet, int itemLayout) {
        this.context = context;
        this.keywordSet = keywordSet;
        this.itemLayout=itemLayout;
    }


    @Override
    public SpeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new SpeViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(SpeViewHolder holder, int position) {
        Keyword keyword = keywordSet.get(position);
        holder.binding.setTwodata(keyword);

    }


    @Override
    public int getItemCount() {
        return (keywordSet !=null) ? keywordSet.size() : 0;
    }

    static class SpeViewHolder extends RecyclerView.ViewHolder{

        ItemSpeBinding binding;



        private SpeViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);

        }
    }
}
