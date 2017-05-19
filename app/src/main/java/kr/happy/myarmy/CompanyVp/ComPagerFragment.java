package kr.happy.myarmy.CompanyVp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.R;
import kr.happy.myarmy.Recyclerview.Data;
import kr.happy.myarmy.Recyclerview.SpeAdapter;
import kr.happy.myarmy.Server.Item;
import kr.happy.myarmy.Server.RetroInterface;
import kr.happy.myarmy.Server.ServerGenerator;
import kr.happy.myarmy.UserDB.UserDBManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ComPagerFragment extends Fragment {
    private int mPage;
    public static final String CUR_PAGE = "CUR_PAGE"; //현재페이지

    @Nullable
    @BindView(R.id.rv_spe)
    RecyclerView mRecyclerview;

    private SpeAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Data> dataSet;
    private ArrayList<Item> dataSet2;
    private String[] itemTitle;
    private String[] itemContent;
    private Item item;
    private String token;
    private int id;
    private static final String CUR_ID = "CUR_ID";

    private UserDBManager mDBManager;

    public ComPagerFragment() {
    }

    public static ComPagerFragment newInstance(int page, int id) {
        ComPagerFragment fragment = new ComPagerFragment();
        Bundle args = new Bundle();

        args.putInt(CUR_PAGE, page);
        args.putInt(CUR_ID, id);
        fragment.setArguments(args);

        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.spe_view, container, false);
        ButterKnife.bind(this, view);

        mRecyclerview.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerview.setLayoutManager(mLayoutManager);

        adapter = new SpeAdapter(getActivity(), dataSet, R.layout.item_spe);

        mRecyclerview.setAdapter(adapter);
        mRecyclerview.setItemAnimator(new DefaultItemAnimator());

        /*
        기업, 채용, 연봉정보 데이터 뿌려주기
         */
        callSpeAPI(ServerGenerator.getRequestService(), id, mPage);

        return view;
    }

    /*
    get data
    */
    public void callSpeAPI(RetroInterface apiService, int id, final int mPage) {
        Call<JsonObject> call = apiService.getSpeList(token, id);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    Gson gson = new Gson();
                    item = gson.fromJson(response.body().get("result").getAsJsonObject(), Item.class);

                    adapter.notifyDataSetChanged();

                    switch (mPage) {
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
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBManager = UserDBManager.getInstance(getActivity());
        dataSet = new ArrayList<>();
        dataSet2 = new ArrayList<>();
        /*
        get TOKEN
         */
        Cursor c = mDBManager.query(new String[]{"token"}, null, null, null, null, null);

        if (c != null && c.moveToFirst())
            token = c.getString(0);

        if (getArguments() != null) {
            mPage = getArguments().getInt(CUR_PAGE);
            id = getArguments().getInt(CUR_ID);
        }
    }

    /*
    set company data method
    */
    private void setCompanyData() {


        itemTitle = new String[]{getString(R.string.website), getString(R.string.bonsa), getString(R.string.daepyoNm),
                getString(R.string.eopjong), getString(R.string.damdangja), getString(R.string.damdangjaNm)};

        itemContent = new String[]{item.getHomepg(), item.getGeunmujy(), item.getDpyeonrakcheoNo(), item.getEopjongGbcdNm(), item.getDamdangjaFnm(), item.getDdjyeonrakcheoNo()};

        dataSet.clear();
        adapter.notifyDataSetChanged();

        for (int i = 0; i < itemTitle.length; i++) {
            if (itemContent[i] == null)
                itemContent[i] = " ";

            dataSet.add(new Data(itemTitle[i], itemContent[i]));
        }
    }

    /*
     set chaeyong data
     */
    private void setChaeyongData() {
        itemTitle = new String[]{getString(R.string.magam), getString(R.string.jiwon), getString(R.string.eopmu), getString(R.string.jiwonJakuk),
                getString(R.string.jeongong), getString(R.string.yeok)};

        itemContent = new String[]{item.hangleMagamDt(), item.getJeopsu(), item.getDdeopmuNm(), item.getGyeongryeokGbcdNm() + " | " + item.getCjhakryeok(),
                item.convertJeonGong(), item.getYeokjongBrcdNm()};

        dataSet.clear();
        adapter.notifyDataSetChanged();

        for (int i = 0; i < itemTitle.length; i++) {
            if (itemContent[i] == null)
                itemContent[i] = " ";

            dataSet.add(new Data(itemTitle[i], itemContent[i]));
        }
    }

    /*
    set yeonbong data
    */
    private void setYeonbongData() {

        itemTitle = new String[]{getString(R.string.bokri), getString(R.string.yeonbong),};
        itemContent = new String[]{item.getBokri(), item.getGyeongryeokGbcdNm()};

        dataSet.clear();
        adapter.notifyDataSetChanged();

        for (int i = 0; i < itemTitle.length; i++) {
            if (itemContent[i] == null)
                itemContent[i] = " ";

            dataSet.add(new Data(itemTitle[i], itemContent[i]));
        }


    }


}
