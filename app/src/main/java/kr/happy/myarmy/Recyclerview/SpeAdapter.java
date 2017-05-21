package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kr.happy.myarmy.databinding.ItemSpeBinding;

/**
 * Created by JY on 2017-05-15.
 */

public class SpeAdapter extends RecyclerView.Adapter<SpeAdapter.SpeViewHolder>{

    private Context context;
    private int itemLayout;
    private ArrayList<TwoData> twoDataSet;

    public SpeAdapter(Context context, ArrayList<TwoData> twoDataSet, int itemLayout) {
        this.context = context;
        this.twoDataSet = twoDataSet;
        this.itemLayout=itemLayout;
    }


    @Override
    public SpeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new SpeViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(SpeViewHolder holder, int position) {
        TwoData twoData=twoDataSet.get(position);
        holder.binding.setTwodata(twoData);

    }


    @Override
    public int getItemCount() {
        return (twoDataSet !=null) ? twoDataSet.size() : 0;
    }

    static class SpeViewHolder extends RecyclerView.ViewHolder{

        ItemSpeBinding binding;



        public SpeViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);

        }
    }
}
