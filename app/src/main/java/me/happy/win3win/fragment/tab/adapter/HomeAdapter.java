package me.happy.win3win.fragment.tab.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import me.happy.win3win.databinding.ItemHomeBinding;
import me.happy.win3win.fragment.CompanyInfoFragment;
import me.happy.win3win.fragment.tab.model.Gonggo;

/*regionviewholder use this?*/
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Gonggo> gongos;
    private int itemLayout; //레이아웃 형식
    private RecyclerView mRecyclerview;
    private FragmentManager fgManager;
    private int frag;

    public HomeAdapter(Context context, List<Gonggo> gongos, int itemLayout, RecyclerView mRecyclerView, FragmentManager fgManager, int frag) {
        this.context = context;
        this.gongos = gongos;
        this.itemLayout = itemLayout;
        this.mRecyclerview = mRecyclerView;
        this.fgManager = fgManager;
        this.frag =frag;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        itemView.setOnClickListener(this);

        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        Gonggo gonggo = gongos.get(position);
        holder.binding.setItem(gonggo);

        /*
        로고 이미지와, 근무지부분 , 연봉부분
         */
        Glide.with(context)
                .load(gongos.get(position).getThumbnail())
                .thumbnail(0.05f)
                .into(holder.binding.itemComLogo);


        holder.binding.itemComContent3.setText(gongos.get(position).convertGeunmujySido() + " | " +
                gongos.get(position).getGyjogeonCdNm());
        holder.binding.itemComContent4.setText(
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
        Gonggo clickGongo = gongos.get(curPos); //클릭한 공고

        CompanyInfoFragment companyInfoFragment = new CompanyInfoFragment();
        /*
        save data
         */

        fgManager
                .beginTransaction()
                .add(frag, companyInfoFragment.newInstance(clickGongo.getId(),  clickGongo.getThumbnail()))
                .addToBackStack(null)
                .commit();
    }


    /*HomeViewHolder class*/
    static class HomeViewHolder extends RecyclerView.ViewHolder {

        ItemHomeBinding binding;


        private HomeViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);;
        }
    }
}

