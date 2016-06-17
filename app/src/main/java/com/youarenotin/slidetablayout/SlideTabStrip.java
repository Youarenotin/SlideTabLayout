package com.youarenotin.slidetablayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 作者：lubo on 6/16 0016 11:15
 * 邮箱：lubo_wen@126.com
 */
public class SlideTabStrip extends LinearLayout implements ViewPager.OnPageChangeListener{
    public SlideTabStrip(Context context) {
        super(context);
    }

    public SlideTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SlideTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
