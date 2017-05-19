package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.happy.myarmy.Menu.CompanyInfoFragment;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Server.Item;

import static kr.happy.myarmy.R.drawable.group_4;
import static kr.happy.myarmy.R.drawable.group_5;

/**
 * Created by JY on 2017-04-15.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.JobViewHolder> implements View.OnClickListener{

    private Context context;
    private ArrayList<Item> gongos;
    private int itemLayout; //레이아웃 형식
    private RecyclerView mRecyclerview;
    private FragmentManager fgManager;

    public GroupAdapter(Context context, ArrayList<Item> gongos, int itemLayout, RecyclerView mRecyclerview, FragmentManager fgManager) {
        this.context = context;
        this.gongos=gongos;
        this.itemLayout = itemLayout;
        this.mRecyclerview=mRecyclerview;
        this.fgManager=fgManager;
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        itemView.setOnClickListener(this);

        return new JobViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, final int position) {
        holder.companyName.setText(gongos.get(position).getEopcheNm());
        holder.companyUpmoo.setText(gongos.get(position).getDdeopmuNm());
        holder.companyLocaPay.setText(gongos.get(position).convertGeunmujySido() + " | " +
                gongos.get(position).getGyjogeonCdNm());
        holder.companyDGyeongEdu.setText(
                (gongos.get(position).convertMagamDt()) + " | "+
                        (gongos.get(position).getGyeongryeokGbcdNm()) + " | " +
                        (gongos.get(position).getCjhakryeok()));

    }

    @Override
    public int getItemCount() {
        return (gongos != null) ? gongos.size() : 0;
    }

    /*show company's info specific*/
    @Override
    public void onClick(View v) {
        int curPos= mRecyclerview.getChildAdapterPosition(v); //클릭된 차일드의 현재 포지션
        Item clickGongo=gongos.get(curPos); //클릭한 공고

        CompanyInfoFragment companyInfoFragment=new CompanyInfoFragment();

        Bundle args=new Bundle();
        args.putInt("CUR_ID", clickGongo.getId());

        fgManager
                .beginTransaction()
                .add(R.id.frag, companyInfoFragment.newInstance(clickGongo.getId()))
                .addToBackStack(null) //saved state
                .commit();
    }

    /*JobViewHolder class*/
    class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_jobLogo) ImageView logo;
        @BindView(R.id.item_jobTitle)TextView companyName; //업체이름
        @BindView(R.id.item_jobContent2)TextView companyUpmoo; //담당업무
        @BindView(R.id.item_jobContent3)TextView companyLocaPay; //지역과 연봉
        @BindView(R.id.item_jobContent4)TextView companyDGyeongEdu; //마감일자(상시채용여부), 경력과 학력

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


