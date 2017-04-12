package kr.happy.myarmy.RecyclerviewHome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<HomeViewHolder> {

    private Context context;
    private ArrayList<ItemHome> items;
    private int itemLayout; //레이아웃 형식

    public RecyclerAdapter(Context context, ArrayList<ItemHome> items, int itemLayout) {
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
    }

    @Override
    public int getItemCount() { //갯수 반환
        return (items!=null) ? items.size() : 0;
    }
}
