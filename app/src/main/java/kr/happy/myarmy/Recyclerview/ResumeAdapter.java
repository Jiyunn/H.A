package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;

/**
 * Created by JY on 2017-04-15.
 */

/*myresume recyclerview adapter*/
public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder> {

    private Context context;
    private ArrayList<Data> items;
    private int itemLayout;


    public ResumeAdapter(Context context, ArrayList<Data> items, int itemLayout) {
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public ResumeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new ResumeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResumeViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.content.setText(items.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public void setItems(ArrayList<Data> items) {
        this.items = items;
    }


    /*ResumeViewHolder class*/
    static class ResumeViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.item_resTitle)
        TextView title;
        @Nullable
        @BindView(R.id.item_resContent)
        TextView content;

        public ResumeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
