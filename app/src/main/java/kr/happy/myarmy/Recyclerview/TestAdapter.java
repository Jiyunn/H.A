package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by JY on 2017-04-15.
 */

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ItemResumenInfo> items;
    private int itemLayout; //레이아웃 형식

    public TestAdapter(Context context, ArrayList<ItemResumenInfo> items, int itemLayout) {
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("jy", String.valueOf(position));
        return position % 2 * 2; //뷰 타입 반환
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;

        switch (viewType) {
            case 0:
                Log.d("jy", "createview holder info");
                itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
                return new InfoViewHolder(itemView);
            case 2:
                Log.d("jy", "createview holder resume");
                itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
                return new ResumeViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof InfoViewHolder) {
            Log.d("jy", String.valueOf(holder.getItemViewType()));
            ((InfoViewHolder) holder).title.setText(items.get(position).getTitle());

        } else if (holder instanceof ResumeViewHolder) {
            Log.d("jy", String.valueOf(holder.getItemViewType()));
            ResumeViewHolder resumeViewHolder = (ResumeViewHolder) holder;
            resumeViewHolder.title.setText(items.get(position).getTitle());
            resumeViewHolder.content.setText(items.get(position).getContent());
        }


    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }
}
