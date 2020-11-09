package com.prabhat.prabhattradingservice.Adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.prabhat.prabhattradingservice.Fragments.IntradayTrades;
import com.prabhat.prabhattradingservice.Fragments.LongTermTrades;
import com.prabhat.prabhattradingservice.Fragments.SwingTrades;

/**
 * Created by abdalla on 2/18/18.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new IntradayTrades();
            case 1:
                return new SwingTrades();
            case 2:
                return new LongTermTrades();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
