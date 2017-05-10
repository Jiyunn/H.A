package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.Menu.CompanyInfoFragment;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Retrofit2.Item;

/*regionviewholder use this?*/
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<Item> gongos;
    private int itemLayout; //레이아웃 형식
    private RecyclerView mRecyclerview;
    private FragmentManager fgManager;

    public HomeAdapter(Context context, ArrayList<Item>gongos, int itemLayout, RecyclerView mRecyclerView, FragmentManager fgManager) {
        this.context = context;
        this.gongos = gongos;
        this.itemLayout = itemLayout;
        this.mRecyclerview=mRecyclerView;
        this.fgManager=fgManager;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        itemView.setOnClickListener(this);

        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.companyName.setText(gongos.get(position).getEopcheNm());
        holder.companyJaemok.setText(gongos.get(position).getCyjemokNm());
        holder.companyUpmoo.setText(gongos.get(position).getDdeopmuNm());
        holder.companyLocaPay.setText(gongos.get(position).convertGeunmujySido() + " | " +
                gongos.get(position).getGyjogeonCdNm());
        holder.companyDGyeongEdu.setText(
                (gongos.get(position).convertMagamDt()) + " | "+
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
        int curPos= mRecyclerview.getChildAdapterPosition(v); //클릭된 차일드의 현재 포지션

        fgManager
                .beginTransaction()
                .replace(R.id.frag, new CompanyInfoFragment())
                .addToBackStack(null) //saved state
                .commit();
    }


    /*HomeViewHolder class*/
    static  class HomeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_comTitle)TextView companyName; //업체이름
        @BindView(R.id.item_comContent1)TextView companyJaemok; //채용제목
        @BindView(R.id.item_comContent2)TextView companyUpmoo; //담당업무
        @BindView(R.id.item_comContent3)TextView companyLocaPay; //지역과 연봉
        @BindView(R.id.item_comContent4)TextView companyDGyeongEdu; //마감일자(상시채용여부), 경력과 학력


        public HomeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

