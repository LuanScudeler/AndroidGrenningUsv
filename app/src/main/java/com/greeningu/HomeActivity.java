package com.greeningu;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.astuetz.PagerSlidingTabStrip;


public class HomeActivity extends ActionBarActivity{

    android.support.v7.app.ActionBar actionbar;
    ViewPager viewpager;
    FragmentPagerAdapter ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        viewpager = (ViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager()));


        actionbar = getSupportActionBar();
        actionbar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
       /* actionbar.addTab(actionbar.newTab().setText("Tab1").setTabListener((android.support.v7.app.ActionBar.TabListener)this));
        actionbar.addTab(actionbar.newTab().setText("Tab2").setTabListener((android.support.v7.app.ActionBar.TabListener)this));
        actionbar.addTab(actionbar.newTab().setText("Tab3").setTabListener((android.support.v7.app.ActionBar.TabListener)this));
        actionbar.addTab(actionbar.newTab().setText("Tab4").setTabListener((android.support.v7.app.ActionBar.TabListener)this));*/

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(viewpager);

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
