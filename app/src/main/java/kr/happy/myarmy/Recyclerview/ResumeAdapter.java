package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.happy.myarmy.databinding.ItemMyresumeBinding;

/**
 * Created by JY on 2017-04-15.
 */

/*myresume recyclerview adapter*/
public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder> {

    private Context context;
    private ArrayList<TwoData> twoDataSet;
    private int itemLayout;


    public ResumeAdapter(Context context, ArrayList<TwoData>twoDataSet, int itemLayout) {
        this.context = context;
        this.twoDataSet =twoDataSet;
        this.itemLayout = itemLayout;
    }

    @Override
    public ResumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ResumeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResumeViewHolder holder, int position) {
        TwoData twoData= twoDataSet.get(position);
        holder.binding.setTwodata(twoData);

    }

    @Override
    public int getItemCount() {
        return (twoDataSet != null) ? twoDataSet.size() : 0;
    }

    public void setItems(ArrayList<TwoData> items) {
        this.twoDataSet = items;
    }


    /*ResumeViewHolder class*/
    static class ResumeViewHolder extends RecyclerView.ViewHolder {

        ItemMyresumeBinding binding;


        public ResumeViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
