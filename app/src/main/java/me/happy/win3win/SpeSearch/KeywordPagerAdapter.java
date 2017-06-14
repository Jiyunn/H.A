package me.happy.win3win.SpeSearch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by JY on 2017-05-22.
 */

public class KeywordPagerAdapter extends FragmentStatePagerAdapter {
    private final int PAGE_CNT =2;
    private final String tabTitle[] =new String[] {"인기검색어", "최근검색어"};


    public KeywordPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return KeywordsFragment.newInstance(position);
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
