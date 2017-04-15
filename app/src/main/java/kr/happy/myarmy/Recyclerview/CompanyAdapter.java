package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/*regionviewholder use this?*/
public class CompanyAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private Context context;
    private ArrayList<ItemHomenJob> items;
    private int itemLayout; //레이아웃 형식

    public CompanyAdapter(Context context, ArrayList<ItemHomenJob> items, int itemLayout) {
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
