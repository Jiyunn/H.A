package me.happy.win3win.fragment.search;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.happy.win3win.R;
import me.happy.win3win.databinding.SpeViewBinding;
import me.happy.win3win.fragment.search.adapter.MySearchAdapter;
import me.happy.win3win.fragment.search.adapter.PopSearchAdapter;
import me.happy.win3win.model.Keyword;
import me.happy.win3win.db.UserDBManager;

/**
 * Created by JY on 2017-05-22.
 * about keyword
 */

public class KeywordsFragment extends Fragment {

    SpeViewBinding binding;

    private int mPage;
    public static final String CUR_PAGE = "CUR_PAGE"; //현재페이지

    private PopSearchAdapter adapterPop;
    private MySearchAdapter adapterMy;
    private LinearLayoutManager mLayoutManager;
    private List<Keyword> dataSet;
    private FragmentManager fgManager;

    private UserDBManager mDBManager;


    public KeywordsFragment() {
    }

    public static KeywordsFragment newInstance(int page) {
        KeywordsFragment fragment = new KeywordsFragment();
        Bundle args = new Bundle();

        args.putInt(CUR_PAGE, page);

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.spe_view, container, false);
        View view = binding.getRoot();

        binding.rvSpe.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        binding.rvSpe.setLayoutManager(mLayoutManager);
        if (mPage == 0) {
            adapterPop = new PopSearchAdapter(getActivity(), dataSet, R.layout.item_popkeyword, fgManager, binding.rvSpe);
            binding.rvSpe.setAdapter(adapterPop);
        } else if (mPage == 1) {
            adapterMy = new MySearchAdapter(getActivity(), dataSet, R.layout.item_mykeyword, fgManager, binding.rvSpe);
            binding.rvSpe.setAdapter(adapterMy);
        }

        binding.rvSpe.setItemAnimator(new DefaultItemAnimator());

        setPopKeyword();

        return view;
    }

    /*
    인기검색어 임의로 셋팅 /  유저의 검색어 디비에서 읽어오기
     */
    protected void setPopKeyword() {
        String[] popKeyword = new String[]{"개발", "보충역", "연구", "연봉 2400만원 이상", "설비", "병역특례정보", "전자기기", "대전광역시", "충청북도지역공고", "의약분야", "현역"};
        String[] myKeyword;

        if (mPage == 0) {
            for (int i = 1; i <= 10; i++)
                dataSet.add(new Keyword(String.valueOf(i), popKeyword[i - 1]));
            adapterPop.notifyDataSetChanged();
        } else if (mPage == 1) {
            myKeyword = getKeywordInDB();

            //검색어 기록이 존재하는 경우만 최근검색어 항목들을 추가한다
            if (myKeyword != null) {

                for (int i = 0; i < myKeyword.length; i++)
                    dataSet.add(new Keyword("", myKeyword[i]));
                adapterMy.notifyDataSetChanged();
            }
        }
    }

    /*
    DB에서 내 검색어 꺼내오기
     */
    protected String[] getKeywordInDB() {
        Cursor c = mDBManager.query(new String[]{"keyword"}, null, null, null, null, null);
        String[] keyword = new String[]{};

        if (c != null && c.moveToFirst() && c.getString(0) != null) {
            keyword = c.getString(0).split(",");
        }
        c.close();
        return keyword;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataSet = new ArrayList<>();

        fgManager = getFragmentManager();
        mDBManager = UserDBManager.getInstance(getActivity());

        if (getArguments() != null)
            mPage = getArguments().getInt(CUR_PAGE);
    }


}
