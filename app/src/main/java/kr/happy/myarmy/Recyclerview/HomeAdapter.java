package kr.happy.myarmy.Recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Retrofit2.Item;

/*regionviewholder use this?*/
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private Context context;
    private ArrayList<Item> gongos;
    private int itemLayout; //레이아웃 형식

    public HomeAdapter(Context context, ArrayList<Item>gongos, int itemLayout) {
        this.context = context;
        this.gongos = gongos;
        this.itemLayout = itemLayout;
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);

        return new HomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) { //뿌려주기
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

    public void setGongos(ArrayList<Item> gongos) {
        this.gongos = gongos;
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

