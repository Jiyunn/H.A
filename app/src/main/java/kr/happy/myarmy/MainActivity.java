package kr.happy.myarmy;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.happy.myarmy.Menu.HomeFragment;
import kr.happy.myarmy.Menu.JobGroupFragment;
import kr.happy.myarmy.Menu.MyResumeFragment;
import kr.happy.myarmy.Menu.RegionGroupFragment;
import kr.happy.myarmy.Menu.SmartMatchFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    private FragmentTransaction fgTransaction;
    private FragmentManager fgManager;

    private BackButtonHandler backButtonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0, 0); //툴바 양쪽 공백없애기

        /*set home tab*/
        bottomBar.setDefaultTab(R.id.tab_HOME);

        fgManager = getSupportFragmentManager();

        backButtonHandler = new BackButtonHandler(this);


        /*tab클릭시*/
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
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

    /*change fragment*/
    private void changeFragment(Fragment fg) {
        fgTransaction = fgManager.beginTransaction();
        fgTransaction.replace(R.id.frag, fg); //change fragment
        fgTransaction.addToBackStack(null); //backstack
        fgTransaction.commit();
    }

    /*back btn */
    @Override
    public void onBackPressed() {
        int fgStackCnt = fgManager.getBackStackEntryCount();
        int home = R.id.tab_HOME;
        //super.onBackPressed();

        if (bottomBar.getCurrentTabId() == home) {
            backButtonHandler.onBackPressed();
        } else {
            bottomBar.selectTabWithId(home);
        }
    }





}
