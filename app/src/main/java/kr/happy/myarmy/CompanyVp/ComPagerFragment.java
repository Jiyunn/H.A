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


public class ComPagerFragment extends Fragment {
    private int mPage;
    public static final String CUR_PAGE = "CUR_PAGE"; //현재페이지

    @Nullable
    @BindView(R.id.rv_spe)
    RecyclerView mRecyclerview;

    private SpeAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Data> dataSet;
    private String[] itemTitle;
    private String[] itemContent;
    private static Item item; //프래그먼트로부터 받아온 item객체

    public ComPagerFragment() {
    }

    public static ComPagerFragment newInstance(int page, Item item) {
        ComPagerFragment fragment = new ComPagerFragment();
        Bundle args = new Bundle();

        args.putInt(CUR_PAGE, page);
        args.putParcelable(Item.class.getName(), item);

        fragment.setArguments(args);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.spe_view, container, false);
        ButterKnife.bind(this, view);

        item = getArguments().getParcelable(Item.class.getName());
        mRecyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter = new SpeAdapter(getActivity(), dataSet, R.layout.item_spe);

        mRecyclerview.setAdapter(adapter);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());


        switch (mPage) { //페이지마다 모두 형식은 같지만,  dataSet만 다르게 뿌려주자.
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            mPage = getArguments().getInt(CUR_PAGE);
        dataSet = new ArrayList<>();
    }

    /* set company data method*/
    private void setCompanyData() {
        itemTitle = new String[]{getString(R.string.website), getString(R.string.bonsa), getString(R.string.daepyoNm),
                getString(R.string.eopjong), getString(R.string.damdangja), getString(R.string.damdangjaNm)};

        itemContent = new String[]{item.getHomepg(), item.getGeunmujy(), item.getDpyeonrakcheoNo(), item.getEopjongGbcdNm(), item.getDamdangjaFnm(), item.getDdjyeonrakcheoNo()};

        dataSet.clear();
        adapter.notifyDataSetChanged();

        for (int i = 0; i < itemTitle.length; i++) {
            if(itemContent[i] == null)
                itemContent[i]=" ";

            dataSet.add(new Data(itemTitle[i], itemContent[i]));
        }
    }

    /* set chaeyong data*/
    private void setChaeyongData() {
        //지원자격 : 신입/경력여부 + 학력 , 고용형태: 역종
        itemTitle = new String[]{getString(R.string.magam), getString(R.string.jiwon), getString(R.string.eopmu), getString(R.string.jiwonJakuk),
                getString(R.string.jeongong), getString(R.string.whygok), getString(R.string.yeok)};

        itemContent= new String[]{item.hangleMagamDt(), item.getJeopsu(), item.getDdeopmuNm(), item.getGyeongryeokGbcdNm()+" | "+item.getCjhakryeok(),
                item.convertJeonGong(), item.getOegukeo()+" "+item.getOegukeoGusa(), item.getYeokjongBrcdNm()};

        dataSet.clear();
        adapter.notifyDataSetChanged();

        for(int i=0; i< itemTitle.length; i++){
            if(itemContent[i] == null)
                itemContent[i]=" ";

            itemContent[i].replace("null"," ");
            dataSet.add(new Data(itemTitle[i], itemContent[i]));
        }
    }

    /*set yeonbong data*/
    private void setYeonbongData() {
        //연봉, 복리후생
        itemTitle = new String[]{getString(R.string.bokri),getString(R.string.yeonbong), };
        itemContent = new String[] {item.getBokri(), item.getGyeongryeokGbcdNm()};

        dataSet.clear();
        adapter.notifyDataSetChanged();

        for(int i=0; i< itemTitle.length; i++){
            if(itemContent[i] == null)
                itemContent[i]=" ";

            itemContent[i].replace("null"," ");
            dataSet.add(new Data(itemTitle[i], itemContent[i]));
        }


    }


}
