package me.happy.win3win.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import me.happy.win3win.custom.BackButtonHandler;
import me.happy.win3win.R;
import me.happy.win3win.databinding.ActivitySplashBinding;
import me.happy.win3win.fragment.login.FirstFragment;


public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    private BackButtonHandler backButtonHandler;
    private FragmentManager fgManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        binding.setActivity(this);

        fgManager = getSupportFragmentManager();
        changeFragment(new FirstFragment());

        backButtonHandler = new BackButtonHandler(this);

    }
    /*
    ATTACH OTHER FRAGMENT
    */
    private void changeFragment(Fragment fg) {
        fgManager
                .beginTransaction()
                .replace(R.id.splash_frag, fg)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (fgManager.getBackStackEntryCount() != 0)
            fgManager.popBackStack();
        else
            backButtonHandler.onBackPressed();
    }


}
