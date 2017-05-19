package kr.happy.myarmy.Menu;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

import kr.happy.myarmy.CompanyVp.ComPagerAdapter;
import kr.happy.myarmy.MainActivity;
import kr.happy.myarmy.R;
import kr.happy.myarmy.databinding.SpeinfoBinding;


public class CompanyInfoFragment extends Fragment {

    SpeinfoBinding binding;

    private BottomBar bottomBar;
    private static int id; //공고의 아이디 번호

    private String speTitle;
    private String speContent;

    public CompanyInfoFragment() {
    }

    /*
    setArgument
     */
    public static CompanyInfoFragment newInstance(int id) {
        Bundle args = new Bundle();
        CompanyInfoFragment fragment = new CompanyInfoFragment();

        args.putInt("CUR_ID", id);

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.speinfo, container, false);
        View view = binding.getRoot();
        binding.setSpeinfo(this);

        binding.speVp.setAdapter(new ComPagerAdapter(getChildFragmentManager() , id));
        binding.speTab.setupWithViewPager(binding.speVp);

        binding.speComTitle.setText(speTitle);
        binding.speComContent1.setText(speContent);

        return view;
    }


    /*    click favorite btn, change background    */
    public void addFavorite(View view) {
        TextView v = (TextView) view;

        if (v.getTag() == null || v.getTag().equals(R.string.notInterested)) {
            v.setBackgroundResource(R.drawable.interested_active);
            v.setTextColor(ContextCompat.getColor(getContext(), R.color.orange_a));
            v.setTag(R.string.interested);
            Toast.makeText(getContext(), "관심기업에 추가되었습니다", Toast.LENGTH_SHORT).show();

        } else if (v.getTag().equals(R.string.interested)) {
            v.setBackgroundResource(R.drawable.interested);
            v.setTextColor(ContextCompat.getColor(getContext(), R.color.nineb));
            Toast.makeText(getContext(), "관심기업이 해제되었습니다", Toast.LENGTH_SHORT).show();
            v.setTag(R.string.notInterested);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() !=null)
            id=getArguments().getInt("CUR_ID");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bottomBar = (BottomBar) (((MainActivity) getContext()).findViewById(R.id.bottom_bar));
        bottomBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPause() {
        bottomBar.setVisibility(View.VISIBLE);
        super.onPause();
    }
}
