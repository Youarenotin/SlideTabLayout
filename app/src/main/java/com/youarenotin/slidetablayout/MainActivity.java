package com.youarenotin.slidetablayout;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements GuideFragment.OnFragmentInteractionListener {
    private List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewpager;
    private SlideTabLayout tab;
    private Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tb = (Toolbar) findViewById(R.id.tb);
        setSupportActionBar(tb);
        for (int i = 0 ; i <getTitles().size() ; i++){
            GuideFragment fragment = GuideFragment.newInstance("", "");
            Bundle bundle = new Bundle();
            bundle.putInt("data", getFragmentBg().get(i));
            bundle.putString("title",getTitles().get(i));
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tab = (SlideTabLayout) findViewById(R.id.tab);
        Adapter adapter = new Adapter(getSupportFragmentManager(),fragments);
        viewpager.setAdapter(adapter);
        tab.setViewPager(viewpager);
    }

    private List<String> getTitles(){
        return Lists.newArrayList("推荐", "英雄联盟", "炉石传说", "三国","龙之谷", "我的世界", "暗黑破坏者", "QQ三国");
    }
    private List<Integer> getFragmentBg(){
        return  Lists.newArrayList(R.drawable.bg2, R.drawable.bg1, R.drawable.bg3, R.drawable.bg4,R.drawable.bg2, R.drawable.bg1, R.drawable.bg3, R.drawable.bg4);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_select_sina:
                Toast.makeText(getApplicationContext(),"sina",Toast.LENGTH_SHORT).show();
                tab.setMode(2);
                break;
            case R.id.menu_select_huya:
                Toast.makeText(getApplicationContext(),"huya",Toast.LENGTH_SHORT).show();
                tab.setMode(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
