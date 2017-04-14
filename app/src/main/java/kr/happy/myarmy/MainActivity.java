package kr.happy.myarmy;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.happy.myarmy.Menu.HomeFragment;
import kr.happy.myarmy.Menu.JobGroupFragment;
import kr.happy.myarmy.Menu.MyResumeFragment;
import kr.happy.myarmy.Menu.RegionGroupFragment;
import kr.happy.myarmy.Menu.SmartMatchFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    FragmentTransaction fgTransaction;
    FragmentManager fgManager;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setContentInsetsAbsolute(0,0); //툴바 양쪽 공백없애기

        context=this;
        fgManager=getSupportFragmentManager();

        /*home fragment*/
        fgTransaction=fgManager.beginTransaction();
        fgTransaction.add(R.id.frag, new HomeFragment());
        fgTransaction.commit();
    }

    /*when click tab,*/
    @OnClick({R.id.tab_resume, R.id.tab_smartmatch, R.id.tab_HOME, R.id.tab_jobgroup, R.id.tab_regiongroup})
    public void tabClickListnener(View v){
        int tabId=v.getId();

        switch (tabId){
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

    /*change fragment*/
    private void changeFragment(Fragment fg){
        fgTransaction=fgManager.beginTransaction();
        fgTransaction.replace(R.id.frag, fg); //change fragment
        fgTransaction.addToBackStack(null); //backstack
        fgTransaction.commit();
    }
}
