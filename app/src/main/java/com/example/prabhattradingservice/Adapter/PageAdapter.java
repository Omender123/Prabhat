package com.example.prabhattradingservice.Adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.prabhattradingservice.Fragments.IntradayTrades;
import com.example.prabhattradingservice.Fragments.LongTermTrades;
import com.example.prabhattradingservice.Fragments.SwingTrades;

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
