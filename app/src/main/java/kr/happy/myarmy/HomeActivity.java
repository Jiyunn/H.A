package kr.happy.myarmy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabhost)
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setTabHost();

    }

    /*add tab*/
    private void setTabHost(){
        tabHost.setup();

        TabHost.TabSpec ts= tabHost.newTabSpec("resume")
                .setContent(R.id.content1)
                .setIndicator(getString(R.string.tab_resume));
        tabHost.addTab(ts);

        ts= tabHost.newTabSpec("smartmatch")
                .setContent(R.id.content1);
        ts.setIndicator(getString(R.string.tab_smartmatch));
        tabHost.addTab(ts);

        ts= tabHost.newTabSpec("home")
                .setContent(R.id.content1);
        ts.setIndicator("HOME");
        tabHost.addTab(ts);

        ts= tabHost.newTabSpec("jopgroup")
                .setContent(R.id.content1)
                .setIndicator(getString(R.string.tab_jobgroup));
        tabHost.addTab(ts);

        ts= tabHost.newTabSpec("regiongroup")
                .setContent(R.id.content1)
                .setIndicator(getString(R.string.tab_regionalgroup));
        tabHost.addTab(ts);
    }

}
