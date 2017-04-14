package kr.happy.myarmy.Recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import kr.happy.myarmy.R;

/**
 * Created by JY on 2017-04-12.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder {

    ImageView logo;
    TextView title;
    TextView content;
    TextView content2;
    TextView content3;



    public HomeViewHolder(View itemView) {
        super(itemView);

       logo=(ImageView)itemView.findViewById(R.id.img_comLogo);
       title=(TextView)itemView.findViewById(R.id.tv_comTitle);
        content =(TextView)itemView.findViewById(R.id.tv_comContent1);
        content2 =(TextView)itemView.findViewById(R.id.tv_comContent2);
        content2 =(TextView)itemView.findViewById(R.id.tv_comContent3);


    }
}
