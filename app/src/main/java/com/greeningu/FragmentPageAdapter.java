package com.greeningu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.greeningu.fragments.createPosts;
import com.greeningu.fragments.infoCommunity;
import com.greeningu.fragments.infoUser;
import com.greeningu.fragments.newPosts;

/**
 * Created by Luan on 16/05/2015.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter{

    private String[] titles = {"Tab1", "Tab2", /*"Tab3", "Tab4"*/};

    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new createPosts();
            case 1:
                return new newPosts();
            /*case 2:
                return new infoCommunity();
            case 3:
                return new infoUser();*/
            default:
                break;
        }

        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int postion){
        return titles[postion];
    }
}
