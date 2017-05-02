package kr.happy.myarmy.CompanyVp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by JY on 2017-05-02.
 */

public class ComPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_CNT =3;
    private final String tabTitle[] =new String[] {"기업정보", "채용정보" , "연봉정보"};


   public ComPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ComPagerFragment.newInstance(position);
    } //send compagerfragment , position .

    @Override
    public int getCount() {
        return PAGE_CNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }


}
