package com.youarenotin.slidetablayout;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements GuideFragment.OnFragmentInteractionListener {
    private List<Fragment> fragments = new ArrayList<>();
    private ViewPager viewpager;
    private SlideTabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        return Lists.newArrayList("推荐", "娱乐", "体育", "游戏","推荐", "娱乐", "体育", "游戏");
    }
    private List<Integer> getFragmentBg(){
        return  Lists.newArrayList(R.drawable.bg2, R.drawable.bg1, R.drawable.bg3, R.drawable.bg4,R.drawable.bg2, R.drawable.bg1, R.drawable.bg3, R.drawable.bg4);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
