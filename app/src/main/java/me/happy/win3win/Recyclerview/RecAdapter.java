package me.happy.win3win.Recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import me.happy.win3win.Menu.CompanyInfoFragment;
import me.happy.win3win.R;
import me.happy.win3win.Server.Item;
import me.happy.win3win.databinding.ItemRecBinding;

/**
 * Created by JY on 2017-05-22.
 */

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> implements View.OnClickListener{


    private Context context;
    private ArrayList<Item> gongos;
    private int itemLayout; //레이아웃 형식
    private RecyclerView mRecyclerview;
    private FragmentManager fgManager;

    public RecAdapter(Context context, ArrayList<Item> gongos, int itemLayout, RecyclerView mRecyclerView, FragmentManager fgManager) {
        this.context = context;
        this.gongos = gongos;
        this.itemLayout = itemLayout;
        this.mRecyclerview = mRecyclerView;
        this.fgManager = fgManager;
    }

    @Override
    public RecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        itemView.setOnClickListener(this);

        return new RecViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecViewHolder holder, int position) {
        Item item = gongos.get(position);
        holder.binding.setItem(item);

        /*
        로고 이미지와, 근무지부분 , 연봉부분
         */
        Glide.with(context)
                .load(gongos.get(position).getThumbnail())
                .thumbnail(0.05f)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.binding.itemRecLogo);

        holder.binding.itemRecContent3.setText(gongos.get(position).convertGeunmujySido() + " | " +
                gongos.get(position).getGyjogeonCdNm());
        holder.binding.itemRecContent4.setText(
                (gongos.get(position).convertMagamDt()) + " | " +
                        (gongos.get(position).getGyeongryeokGbcdNm()) + " | " +
                        (gongos.get(position).getCjhakryeok()));
    }

    @Override
    public int getItemCount() { //갯수 반환
        return (gongos != null) ? gongos.size() : 0;
    }

    /*show company's info specific*/
    @Override
    public void onClick(View v) {
        int curPos = mRecyclerview.getChildAdapterPosition(v); //클릭된 차일드의 현재 포지션
        Item clickGongo = gongos.get(curPos); //클릭한 공고

        CompanyInfoFragment companyInfoFragment = new CompanyInfoFragment();
        /*
        save data
         */

        fgManager
                .beginTransaction()
                .add(R.id.frag, companyInfoFragment.newInstance(clickGongo.getId(), clickGongo.getThumbnail()))
                .addToBackStack(null) //saved state
                .commit();
    }



    static class RecViewHolder extends RecyclerView.ViewHolder{

        ItemRecBinding binding;

        public RecViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
