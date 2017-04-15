package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;

/*regionviewholder use this?*/
public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private Context context;
    private ArrayList<ItemHomenJob> items;
    private int itemLayout; //레이아웃 형식

    public HomeAdapter(Context context, ArrayList<ItemHomenJob> items, int itemLayout) {
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) { //뿌려주기
        holder.logo.setImageResource(items.get(position).getImg());
        holder.title.setText(items.get(position).getTitle());
        holder.content1.setText(items.get(position).getContent1());
        holder.content2.setText(items.get(position).getContent2());
        holder.content3.setText(items.get(position).getContent3());
    }

    @Override
    public int getItemCount() { //갯수 반환
        return (items!=null) ? items.size() : 0;
    }
}

    /*HomeViewHolder class*/
    class HomeViewHolder extends RecyclerView.ViewHolder {

    @Nullable
    @BindView(R.id.item_comLogo)ImageView logo;
    @Nullable @BindView(R.id.item_comTitle)TextView title;
    @Nullable @BindView(R.id.item_comContent1)TextView content1;
    @Nullable @BindView(R.id.item_comContent2)TextView content2;
    @Nullable @BindView(R.id.item_comContent3)TextView content3;


    public HomeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
