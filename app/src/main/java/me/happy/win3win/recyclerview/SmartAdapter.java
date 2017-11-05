package me.happy.win3win.recyclerview;

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

import me.happy.win3win.menu.CompanyInfoFragment;
import me.happy.win3win.network.Item;
import me.happy.win3win.databinding.ItemHomeBinding;

/**
 * Created by JY on 2017-05-24.
 */

public class SmartAdapter extends RecyclerView.Adapter<SmartAdapter.SmartViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<Item> gongos;
    private int itemLayout; //레이아웃 형식
    private RecyclerView mRecyclerview;
    private FragmentManager fgManager;
    private int frag;

    public SmartAdapter(Context context, ArrayList<Item> gongos, int itemLayout, RecyclerView mRecyclerView, FragmentManager fgManager, int frag) {
        this.context = context;
        this.gongos = gongos;
        this.itemLayout = itemLayout;
        this.mRecyclerview = mRecyclerView;
        this.fgManager = fgManager;
        this.frag =frag;
    }

    @Override
    public SmartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        itemView.setOnClickListener(this);

        return new SmartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SmartViewHolder holder, int position) {
        Item item = gongos.get(position);
        holder.binding.setItem(item);

        /*
        로고 이미지와, 근무지부분 , 연봉부분
         */

        Glide.with(context)
                .load(gongos.get(position).getThumbnail())
                .thumbnail(0.05f)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.binding.itemComLogo);


        holder.binding.itemComContent3.setText(gongos.get(position).convertGeunmujySido() + " | " +
                gongos.get(position).getGyjogeonCdNm());
        holder.binding.itemComContent4.setText(
                (gongos.get(position).convertMagamDt()) + " | " +
                        (gongos.get(position).getGyeongryeokGbcdNm()) + " | " +
                        (gongos.get(position).getCjhakryeok()));
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
                .add(frag, companyInfoFragment.newInstance(clickGongo.getId(), clickGongo.getThumbnail()))
                .addToBackStack(null)
                .commit();
    }




    @Override
    public int getItemCount() {
        return ( gongos !=null) ? gongos.size() : 0;
    }

    public void setGongos(ArrayList<Item> gongos) {
        this.gongos = gongos;
    }

    static class SmartViewHolder extends RecyclerView.ViewHolder{

        ItemHomeBinding binding;


        public SmartViewHolder(View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
