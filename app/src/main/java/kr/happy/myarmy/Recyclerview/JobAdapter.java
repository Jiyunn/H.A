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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;

import static kr.happy.myarmy.R.drawable.group_4;
import static kr.happy.myarmy.R.drawable.group_5;

/**
 * Created by JY on 2017-04-15.
 */

public class JobAdapter extends RecyclerView.Adapter<JobViewHolder> {

    private Context context;
    private ArrayList<ItemHomenJob> items;
    private int itemLayout; //레이아웃 형식

    public JobAdapter(Context context, ArrayList<ItemHomenJob> items, int itemLayout) {
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new JobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, final int position) {
        holder.logo.setImageResource(items.get(position).getImg());
        holder.title.setText(items.get(position).getTitle());
        holder.content1.setText(items.get(position).getContent1());
        holder.content2.setText(items.get(position).getContent2());
        holder.content3.setText(items.get(position).getContent3());

        holder.favorite.setOnClickListener(new View.OnClickListener() { //즐겨찾기 버튼 클릭했을 때,
            @Override
            public void onClick(View v) {

                if (v.getTag().equals("group_5")) {
                    v.setBackgroundResource(group_4);
                    v.setTag("group_4");
                } else {
                    v.setBackgroundResource(group_5);
                    v.setTag("group_5");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (items != null) ? items.size() : 0;
    }

}

/*JobViewHolder class*/
class JobViewHolder extends RecyclerView.ViewHolder {

    @Nullable
    @BindView(R.id.item_jobLogo)
    ImageView logo;
    @Nullable
    @BindView(R.id.item_favorite)
    ImageButton favorite;
    @Nullable
    @BindView(R.id.item_jobTitle)
    TextView title;
    @Nullable
    @BindView(R.id.item_jobContent1)
    TextView content1;
    @Nullable
    @BindView(R.id.item_jobContent2)
    TextView content2;
    @Nullable
    @BindView(R.id.item_jobContent3)
    TextView content3;

    public JobViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}

