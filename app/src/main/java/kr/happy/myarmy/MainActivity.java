package kr.happy.myarmy;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

        setTabHost(); //탭 설정
    }


    /*add tab*/
    private void setTabHost(){
        tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);


        tabHost.addTab(tabHost.newTabSpec("myresume")
            .setIndicator(createTabView(getString(R.string.tab_resume),R.drawable.rr)),
                MyResumeFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("smartmatch")
                .setIndicator(createTabView( getString(R.string.tab_smartmatch),R.drawable.ss)),
                SmartMatchFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("HOME")
                .setIndicator(createTabView("HOME", R.drawable.hh)),
                HomeFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("jobgroup")
                .setIndicator(createTabView(getString(R.string.tab_jobgroup),R.drawable.jj)),
                JobGroupFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("regiongroup")
                .setIndicator(createTabView( getString(R.string.tab_regionalgroup),R.drawable.rrr)),
                RegionGroupFragment.class, null);

           tabHost.setCurrentTab(2); //초기 탭 설정
    }


    /* create tab icon view*/
    private View createTabView( final String title,final int id){
        View tabIcon= LayoutInflater.from(this).inflate(R.layout.tabicon, null);

        ImageView imgTab=(ImageView)tabIcon.findViewById(R.id.img_tab);
        TextView tvTab=(TextView)tabIcon.findViewById(R.id.tv_tab);

        imgTab.setImageResource(id);
        tvTab.setText(title);

        return tabIcon;
    }



}
