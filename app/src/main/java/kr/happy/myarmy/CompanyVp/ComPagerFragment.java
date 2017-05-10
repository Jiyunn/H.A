package kr.happy.myarmy.CompanyVp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.Object;


public class ComPagerFragment extends Fragment{
    private int mPage;
    public static final String CUR_PAGE="CUR_PAGE";

    @Nullable
    @BindView(R.id.rv_spe)RecyclerView mRecyclerview;

    ArrayList<Object> dataSet;


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
        View view = inflater.inflate(R.layout.spe_view, container, false);
        ButterKnife.bind(this, view);

        switch (mPage){ //페이지마다 모두 형식은 같지만,  dataSet만 다르게 뿌려주자.
            case 0:

                break;
            case 1:

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
        dataSet=new ArrayList<>();
    }
}
