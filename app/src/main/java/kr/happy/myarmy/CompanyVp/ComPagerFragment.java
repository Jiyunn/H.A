package kr.happy.myarmy.CompanyVp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import kr.happy.myarmy.R;


public class ComPagerFragment extends Fragment{
    private int mPage;
    public static final String CUR_PAGE="CUR_PAGE";

    public ComPagerFragment() {}

    public static ComPagerFragment newInstance(int page) { //자신을 생성하는 newInstance정적메소드
        ComPagerFragment cFragment = new ComPagerFragment();
        Bundle args = new Bundle();

        if(args !=null)
            args.putInt(CUR_PAGE, page);

        cFragment.setArguments(args);

        return cFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.com_view, container, false);
        ButterKnife.bind(this, view);

        switch (mPage){
            case 0:
                view.setBackgroundColor(getResources().getColor(R.color.navy_a));
                break;
            case 1:
                view.setBackgroundColor(getResources().getColor(R.color.ivory_a));
                break;
        }

       return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(CUR_PAGE); //pageinfo
    }
}
