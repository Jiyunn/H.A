package me.happy.win3win.fragment.tab.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.happy.win3win.databinding.ItemMyresumeBinding;
import me.happy.win3win.model.Keyword;


/**
 * Created by JY on 2017-04-15.
 */

/*myresume recyclerview adapter*/
public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder> {

    private Context context;
    private List<Keyword> keywordSet;
    private int itemLayout;


    public ResumeAdapter(Context context, List<Keyword> keywordSet, int itemLayout) {
        this.context = context;
        this.keywordSet = keywordSet;
        this.itemLayout = itemLayout;
    }

    @Override
    public ResumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ResumeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResumeViewHolder holder, int position) {
        Keyword keyword = keywordSet.get(position);
        holder.binding.setTwodata(keyword);

    }

    @Override
    public int getItemCount() {
        return (keywordSet != null) ? keywordSet.size() : 0;
    }

    public void setItems(List<Keyword> items) {
        this.keywordSet = items;
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
