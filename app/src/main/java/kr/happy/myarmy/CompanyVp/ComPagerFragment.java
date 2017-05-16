package kr.happy.myarmy.CompanyVp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.Data;
import kr.happy.myarmy.Recyclerview.SpeAdapter;
import kr.happy.myarmy.Server.Item;


public class ComPagerFragment extends Fragment{
    private int mPage;
    public static final String CUR_PAGE="CUR_PAGE"; //현재페이지

    @Nullable
    @BindView(R.id.rv_spe)RecyclerView mRecyclerview;

    private SpeAdapter adapter; //어댑터 재활용
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Data> dataSet;
    private String[] itemTitle;
    private Item item; //프래그먼트로부터 받아온 item객체

    public ComPagerFragment() {}

    public static ComPagerFragment newInstance(int page) {
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

        dataSet=new ArrayList<>();

        mRecyclerview.setHasFixedSize(true);

        mLayoutManager=new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter=new SpeAdapter(getActivity(), dataSet, R.layout.item_spe);

        mRecyclerview.setAdapter(adapter);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());


        switch (mPage){ //페이지마다 모두 형식은 같지만,  dataSet만 다르게 뿌려주자.
            case 0:
                setCompanyData();
                break;
            case 1:
                setChaeyongData();
                break;
            case 2:
                setYeonbongData();
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

    /* set company data method*/
    private void setCompanyData(){
        //홈피, 근무지주소, 대표연락처,
        itemTitle=new String[]{"웹사이트", "본사", "대표번호", "업종", "담당자" ,"담당자번호"};
        dataSet.add(new Data(itemTitle[0], "오호"));
    }

    /* set chaeyong data*/
    private void setChaeyongData(){
        //신입/경력구분, 채용제목, 담당업무, 최종학력, 외국어요구, 외국어능력등급, 현역/보충역 구분
        itemTitle=new String[]{"마감일자", "지원방법", "내용", "담당업무", "학력", "전공","외국어", "역종"};
    }

    /*set yeonbong data*/
    private void setYeonbongData(){
        //연봉, 복리후생
        itemTitle=new String[]{"연봉" , "복리후생"};

    }


}
