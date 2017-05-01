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


public class InfoAdapter extends RecyclerView.Adapter<InfoViewHolder> {

    private Context context;
    private ArrayList<ItemResumenInfo> items;
    private int itemLayout;

    public InfoAdapter(Context context, ArrayList<ItemResumenInfo> items, int itemLayout) {
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new InfoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.content.setText(items.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return (items !=null) ? items.size() : 0;
    }
}

 /*info view holder*/

class InfoViewHolder extends RecyclerView.ViewHolder {
    @Nullable @BindView(R.id.item_infoTitle) TextView title;
    @Nullable @BindView(R.id.item_infoContent) TextView content;

    public InfoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
