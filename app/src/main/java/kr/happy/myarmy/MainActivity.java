package kr.happy.myarmy;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.happy.myarmy.Custom.BackButtonHandler;
import kr.happy.myarmy.Menu.HomeFragment;
import kr.happy.myarmy.Menu.JobGroupFragment;
import kr.happy.myarmy.Menu.MyResumeFragment;
import kr.happy.myarmy.Menu.RegionGroupFragment;
import kr.happy.myarmy.Menu.SmartMatchFragment;


public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_search)
    EditText search;

    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;


    private FragmentManager fgManager;
    private BackButtonHandler backButtonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0, 0); //툴바 양쪽 공백없애기
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        bottomBar.setDefaultTab(R.id.tab_HOME);

        fgManager = getSupportFragmentManager();
        backButtonHandler = new BackButtonHandler(this);


        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
                    @Override
                    public void onTabSelected(@IdRes int tabId) {
                        bottomBar.getShySettings().showBar(); //다시보이게

                        switch (tabId) {
                            case R.id.tab_resume:
                                changeFragment(new MyResumeFragment());
                                break;

                            case R.id.tab_smartmatch:
                                changeFragment(new SmartMatchFragment());
                                break;

                            case R.id.tab_HOME:
                                changeFragment(new HomeFragment());
                                break;

                            case R.id.tab_jobgroup:
                                changeFragment(new JobGroupFragment());
                                break;

                            case R.id.tab_regiongroup:
                                changeFragment(new RegionGroupFragment());
                                break;
                        }
                    }
                });
            }
        });

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("jy", "onconfig?");
        super.onConfigurationChanged(newConfig);
    }

    /*back!*/
    @OnClick(R.id.toolbar_back)
    public void backArrowClick(View v){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }


    /*change fragment*/
    private void changeFragment(Fragment fg) {

        fgManager
                .beginTransaction()
                .replace(R.id.frag, fg)
                .commit(); //change fragment
    }

    /*back btn */
    @Override
    public void onBackPressed() {
        int fgStackCnt = fgManager.getBackStackEntryCount();
        int curTab = bottomBar.getCurrentTabId();
        int homeTab = R.id.tab_HOME;

        if (curTab == homeTab) {
            if(fgStackCnt ==0)
                 backButtonHandler.onBackPressed();
            else
                fgManager.popBackStack();
        } else if (curTab == R.id.tab_resume
                || curTab == R.id.tab_jobgroup
                || curTab == R.id.tab_regiongroup) {
            if (fgStackCnt != 0)
                fgManager.popBackStack();
            else
                bottomBar.selectTabWithId(homeTab);

        } else {
            bottomBar.selectTabWithId(homeTab);
        }
    }


}

