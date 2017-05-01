package kr.happy.myarmy.Menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import kr.happy.myarmy.R;

/**
 * Created by JY on 2017-04-11.
 */

public class RegionGroupFragment extends Fragment {



    public RegionGroupFragment(){} //기본생성자

      @Nullable
    @Override //뷰 생성
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View view=inflater.inflate(R.layout.regiongroup, container, false);
          ButterKnife.bind(this, view);


          return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDetach() {
        Log.d("jy", "regiongroup fragment detach");
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onDestroy() {
        Log.d("jy", "region fragment destroy");
        super.onDestroy();
    }
}
