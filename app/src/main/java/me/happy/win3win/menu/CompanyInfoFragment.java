package me.happy.win3win.menu;

import android.database.Cursor;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.roughike.bottombar.BottomBar;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.happy.win3win.MainActivity;
import me.happy.win3win.R;
import me.happy.win3win.network.Item;
import me.happy.win3win.network.RetroInterface;
import me.happy.win3win.network.ServerGenerator;
import me.happy.win3win.search.ComPagerAdapter;
import me.happy.win3win.userdb.UserDBManager;
import me.happy.win3win.databinding.SpeinfoBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompanyInfoFragment extends Fragment {

    SpeinfoBinding binding;

    private BottomBar bottomBar;
    private static int id; //공고의 아이디 번호

    private String speLogo;

    private Item item;
    private String token;

    private UserDBManager mDBManager;


    public CompanyInfoFragment() {
    }

    /*
    setArgument
     */
    public static CompanyInfoFragment newInstance(int id, String speLogo) {
        Bundle args = new Bundle();
        CompanyInfoFragment fragment = new CompanyInfoFragment();

        args.putInt("CUR_ID", id);
        args.putString("SpeLogo", speLogo);

        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.speinfo, container, false);
        View view = binding.getRoot();
        binding.setSpeinfo(this);
        ButterKnife.bind(this, view);

        callSpeAPI(ServerGenerator.getRequestService(), id);


        binding.speComLogo.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(getActivity())
                        .load(speLogo)
                        .thumbnail(0.05f)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(binding.speComLogo);
            }
        });

        binding.speVp.setAdapter(new ComPagerAdapter(getChildFragmentManager() , id));
        binding.speTab.setupWithViewPager(binding.speVp);

        return view;
    }

    /*
   get data
   */
    public void callSpeAPI(RetroInterface apiService, int id) {
        Call<JsonObject> call = apiService.getSpeList(token, id);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    Gson gson = new Gson();
                    item = gson.fromJson(response.body().get("result").getAsJsonObject(), Item.class);

                    binding.speComTitle.setText(item.getEopcheNm());
                    binding.speComContent1.setText(item.getCyjemoknm());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    /*
    click favorite btn, change background
     */
    @OnClick(R.id.spe_comFavorite)
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

        mDBManager = UserDBManager.getInstance(getActivity());

        Cursor c = mDBManager.query(new String[]{"token"}, null, null, null, null, null);

        if (c != null && c.moveToFirst())
            token = c.getString(0);


        if(getArguments() !=null) {
            id = getArguments().getInt("CUR_ID");
            speLogo = getArguments().getString("SpeLogo");
        }

        /*
        get TOKEN
         */



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() instanceof MainActivity) {
            bottomBar = (BottomBar) (((MainActivity) getContext()).findViewById(R.id.bottom_bar));
            bottomBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPause() {
        if (getActivity() instanceof MainActivity)
        bottomBar.setVisibility(View.VISIBLE);
        super.onPause();
    }
}
