package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.happy.myarmy.R;

import static kr.happy.myarmy.R.drawable.group_4;
import static kr.happy.myarmy.R.drawable.group_5;

/**
 * Created by JY on 2017-04-15.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.JobViewHolder> {

    private Context context;
    private ArrayList<ItemHomenJob> items;
    private int itemLayout; //레이아웃 형식
    private View.OnClickListener clickEvent;

    public GroupAdapter(Context context, ArrayList<ItemHomenJob> items, int itemLayout, View.OnClickListener clickEvent) {
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
        this.clickEvent = clickEvent;
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        itemView.setOnClickListener(clickEvent);
        return new JobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, final int position) {
        holder.logo.setImageResource(items.get(position).getImg());
        holder.title.setText(items.get(position).getTitle());
        holder.content1.setText(items.get(position).getContent1());
        holder.content2.setText(items.get(position).getContent2());
        holder.content3.setText(items.get(position).getContent3());

    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

    public void setItems(ArrayList<ItemHomenJob> items) {
        this.items = items;
    }

    /*JobViewHolder class*/
    class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Nullable        @BindView(R.id.item_jobLogo)
        ImageView logo;
        @Nullable        @BindView(R.id.item_jobFavorite)
        ImageButton favorite;
        @Nullable        @BindView(R.id.item_jobTitle)
        TextView title;
        @Nullable        @BindView(R.id.item_jobContent1)
        TextView content1;
        @Nullable        @BindView(R.id.item_jobContent2)
        TextView content2;
        @Nullable        @BindView(R.id.item_jobContent3)
        TextView content3;

        public JobViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_jobFavorite)
        public void onClick(View v) {
            if (v.getId() == R.id.item_jobFavorite) {
                if (v.getTag().equals("group_5")) {
                    v.setBackgroundResource(group_4);
                    v.setTag("group_4");
                    Toast.makeText(context, "관심기업에 추가되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    v.setBackgroundResource(group_5);
                    v.setTag("group_5");
                    Toast.makeText(context, "관심기업이 해제되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
}


