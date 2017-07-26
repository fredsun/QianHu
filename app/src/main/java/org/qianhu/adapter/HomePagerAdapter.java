package org.qianhu.adapter;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.qianhu.app.ui.fragment.DiscoveryFragment;
import org.qianhu.app.ui.fragment.HomeFragment;
import org.qianhu.app.ui.fragment.MeFragment;
import org.qianhu.app.ui.fragment.MessageFragment;
import org.qianhu.app.ui.fragment.NotificationFragment;

/**
 * Created by fred on 2017/7/25.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    public static final int PAGE_COUNT = 3;
    private Context mContext;

    public HomePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        int type;
        switch (position){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new DiscoveryFragment();
                break;
            case 2:
                fragment = new NotificationFragment();
                break;
            case 3:
                fragment = new MessageFragment();
                break;
            case 4:
                fragment = new MeFragment();
                break;
            default:
                fragment = new HomeFragment();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "home";
            case 1:
                return "discover";
            case 2:
                return "notification";
            case 3:
                return "chat";
            case 4:
                return "me";
            default:
                return "home";
        }
    }
}
