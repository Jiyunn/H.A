package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Server.Item;

/**
 * Created by JY on 2017-05-15.
 */

public class SpeAdapter extends RecyclerView.Adapter<SpeAdapter.SpeViewHolder>{

    private Item gongo;
    private Context context;
    private int itemLayout;
    private ArrayList<Data> dataSet;

    public SpeAdapter(Context context, ArrayList<Data> dataSet,int itemLayout) {
        this.context = context;
        this.dataSet=dataSet;
        this.itemLayout=itemLayout;
    }

    @Override
    public SpeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new SpeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SpeViewHolder holder, int position) {
        holder.title.setText(dataSet.get(position).getTitle());
        holder.content.setText(dataSet.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return (dataSet !=null) ? dataSet.size() : 0;
    }

    static class SpeViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_speTitle)TextView title;
        @BindView(R.id.item_speContent) TextView content;

        public SpeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
