package kr.happy.myarmy.Recyclerview;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;

/**
 * Created by JY on 2017-04-15.
 */

/*my resume-> information*/
public class ResumeViewHolder extends RecyclerView.ViewHolder {
    @Nullable @BindView(R.id.item_resTitle) TextView title;
    @Nullable @BindView(R.id.item_resContent) TextView content;

    public ResumeViewHolder(View itemView) {
        super(itemView);        ButterKnife.bind(this, itemView);
    }
}
