package kr.happy.myarmy;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

    @BindView(R.id.tabhost)
    FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTabHost();
    }

    /*add tab*/
    private void setTabHost(){
        tabHost.setup(this, getSupportFragmentManager(), R.id.content);

        tabHost.addTab(tabHost.newTabSpec("myresume")
            .setIndicator(getString(R.string.tab_resume)), MyResumeFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("smartmatch")
                .setIndicator(getString(R.string.tab_smartmatch)), SmartMatchFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("HOME")
                .setIndicator("HOME"), HomeFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("jobgroup")
                .setIndicator(getString(R.string.tab_jobgroup)), JobGroupFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("regiongroup")
                .setIndicator(getString(R.string.tab_regionalgroup)), RegionGroupFragment.class, null);


           tabHost.setCurrentTab(2); //초기 탭 설정
    }

}
