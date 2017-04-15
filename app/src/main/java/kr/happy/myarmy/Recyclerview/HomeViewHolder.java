package kr.happy.myarmy.Recyclerview;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;

/**
 * Created by JY on 2017-04-12.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder {

    @Nullable @BindView(R.id.item_comLogo)ImageView logo;
    @Nullable @BindView(R.id.item_comTitle)TextView title;
    @Nullable @BindView(R.id.item_comContent1)TextView content1;
    @Nullable @BindView(R.id.item_comContent2)TextView content2;
    @Nullable @BindView(R.id.item_comContent3)TextView content3;


    public HomeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
