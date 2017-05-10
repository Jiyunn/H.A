package kr.happy.myarmy.Menu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.happy.myarmy.CompanyVp.ComPagerAdapter;
import kr.happy.myarmy.R;


public class CompanyInfoFragment extends Fragment {

    @Nullable
    @BindView(R.id.spe_vp)
    ViewPager vp;

    @Nullable
    @BindView(R.id.spe_tab)
    TabLayout tabLayout;

    public CompanyInfoFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.speinfo, container, false);
        ButterKnife.bind(this, view);

        vp.setAdapter(new ComPagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(vp);

        return view;
    }

    /*click favorite btn, change background*/
    @OnClick(R.id.spe_comFavorite)
    public void addFavorite(View view) {
        TextView v=(TextView) view;

        if (v.getTag().equals(R.string.notInterested)) {
            v.setBackgroundResource(R.drawable.interested_active);
            v.setTextColor(ContextCompat.getColor(getContext(), R.color.orange_a));
            Toast.makeText(getContext(), "관심기업에 추가되었습니다", Toast.LENGTH_SHORT).show();
            v.setTag(R.string.interested);

        } else {
            v.setBackgroundResource(R.drawable.interested);
            v.setTextColor(Color.parseColor("#9b9b9b"));
            Toast.makeText(getContext(), "관심기업이 해제되었습니다", Toast.LENGTH_SHORT).show();
            v.setTag(R.string.notInterested);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
