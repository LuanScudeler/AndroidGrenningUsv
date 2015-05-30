package com.greeningu;

import android.content.Intent;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.astuetz.PagerSlidingTabStrip;
import com.google.gson.Gson;
import com.greeningu.bean.Usuario;



public class HomeActivity extends ActionBarActivity implements android.support.v7.app.ActionBar.TabListener {

    android.support.v7.app.ActionBar actionbar;
    ViewPager viewpager;
    FragmentPagerAdapter ft;
    String usuarioJson;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(getIntent().getExtras() != null){
            usuarioJson = getIntent().getExtras().getString("usuario");
            usuario = new Gson().fromJson(usuarioJson,Usuario.class);
        }


        viewpager = (ViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager()));


        actionbar = getSupportActionBar();
        actionbar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);
        actionbar.addTab(actionbar.newTab().setText("Criar postagem").setTabListener(this));
        actionbar.addTab(actionbar.newTab().setText("Novas Postagens").setTabListener(this));
        /*actionbar.addTab(actionbar.newTab().setText("Comunidade").setTabListener(this));
        actionbar.addTab(actionbar.newTab().setText("Usuário").setTabListener(this));*/

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(viewpager);

        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == 0){
                    //Toast.makeText(HomeActivity.this,"0",Toast.LENGTH_LONG).show();

                } else if(position == 1){
                    //Toast.makeText(HomeActivity.this,"1",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onPageSelected(int position) {
                actionbar.setSelectedNavigationItem(position);
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

        switch (id){
            case R.id.menu_infouser:
                Intent intent = new Intent(this, DetalhesUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_infocommunity:
                Intent intent2 = new Intent(this, DetalhesComunidadeActivity.class);
                startActivity(intent2);
                return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }
}
