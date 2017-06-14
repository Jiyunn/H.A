package me.happy.win3win.Splash;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.happy.win3win.R;
import me.happy.win3win.databinding.FirstBinding;

/**
 * Created by JY on 2017-05-30.
 */

public class FirstFragment extends Fragment {

    private FirstBinding binding;

    private FragmentManager fgManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fgManager = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , R.layout.first, container, false);
        View view = binding.getRoot();
        binding.setFirst(this);
        ButterKnife.bind(this, view);


        return view;
    }

    /*
   CLICK BTN, ATTACH THAT FRAGMENT
    */
    @OnClick({R.id.signIn, R.id.signUp})
    public void goToMenu(View v) {
        Log.d("jy", "뭐야..");
        if (v.getId() == R.id.signIn)
            changeFragment(new LoginFragment());


        else if (v.getId() == R.id.signUp)
            changeFragment(new JoinFragment());
    }

    /*
  ATTACH OTHER FRAGMENT
  */
    private void changeFragment(Fragment fg) {
        fgManager
                .beginTransaction()
                .replace(R.id.splash_frag, fg)
                .addToBackStack(null)
                .commit();
    }

}
