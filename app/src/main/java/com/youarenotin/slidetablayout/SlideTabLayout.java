package com.youarenotin.slidetablayout;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 作者：lubo on 6/16 0016 11:02
 * 邮箱：lubo_wen@126.com
 */
public class SlideTabLayout extends HorizontalScrollView{

    private static final int TITLE_OFFSET_DIPS = 75;
    private static final int TAB_VIEW_PADDING_DIPS = 15;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;
    private final SlideTabStrip mTabStrip;
    private ViewPager mViewPager;
    private boolean mDistributeEvenly = true;
    private int mTitleOffset;
    private int mode=1;

    public SlideTabLayout(Context context) {
        this(context,null);
    }

    public SlideTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setHorizontalFadingEdgeEnabled(true);
        setHorizontalScrollBarEnabled(false);

        //得到select section 距离左边像素距离
        mTitleOffset = (int) (TITLE_OFFSET_DIPS*(getResources().getDisplayMetrics().density));
        setFillViewport(true);
        mTabStrip = new SlideTabStrip(context);
        addView(mTabStrip, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    public void setViewPager(ViewPager vp){
        this.mViewPager = vp;
        if (mViewPager!=null){
            mViewPager.setOnPageChangeListener(new InternalOnPageChangeListener());
            invalidateTabStrip();
        }
    }

    public void  setMode(int m){
        if (m==1){
            invalidateTabStrip();
        }
        else if(m==2){
            invalidateTabStripBeta();
        }
    }

    private void invalidateTabStripBeta() {
        mTabStrip.removeAllViews();
           this.mode=2;
        this.setBackgroundResource(android.R.color.holo_green_light);
        PagerAdapter adapter = mViewPager.getAdapter();
        for (int i = 0 ; i < adapter.getCount() ; i++){
            TextView textView = new TextView(getContext());
            textView.setText(adapter.getPageTitle(i));
            if (mDistributeEvenly){
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) (20*(getResources().getDisplayMetrics().density)));
                textView.setLayoutParams(lp);
            }
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setGravity(Gravity.CENTER);
            textView.setAllCaps(true);
            int padding = (int) (TAB_VIEW_PADDING_DIPS * getResources().getDisplayMetrics().density);
            textView.setPadding(padding,0,padding,0);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TAB_VIEW_TEXT_SIZE_SP);
            textView.setTextColor(getResources().getColorStateList(R.color.selector_tab_text_color1));
            if (i==0) {
                textView.setSelected(true);
            }
            mTabStrip.addView(textView);
        }
    }

    private void invalidateTabStrip() {
        mTabStrip.removeAllViews();
        this.mode=1;
        this.setBackgroundResource(android.R.color.white);
        PagerAdapter adapter = mViewPager.getAdapter();
        for (int i = 0 ; i < adapter.getCount() ; i++){
            TextView textView = new TextView(getContext());
            textView.setText(adapter.getPageTitle(i));
            if (mDistributeEvenly){
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) (20*(getResources().getDisplayMetrics().density)));
                textView.setLayoutParams(lp);
            }
            textView.setTypeface(Typeface.DEFAULT_BOLD);
            textView.setGravity(Gravity.CENTER);
            textView.setAllCaps(true);
            int padding = (int) (TAB_VIEW_PADDING_DIPS * getResources().getDisplayMetrics().density);
            textView.setPadding(padding,0,padding,0);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TAB_VIEW_TEXT_SIZE_SP);
//            TypedValue value = new TypedValue();
//            getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground,value,true);
            textView.setTextColor(getResources().getColorStateList(R.color.selector_tab_text_color));
            if (i==0) {
                textView.setSelected(true);
                textView.setBackgroundResource(R.drawable.bg_tabview_selected);
            }
            mTabStrip.addView(textView);
        }
    }

    public void setDistributeEvenly(boolean b){
        this.mDistributeEvenly = b;
    }

    public  class InternalOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mode==1){

            }
            else if (mode==2){
                View view = mTabStrip.getChildAt(position);
                scrollToTab(position, (int) (positionOffset*view.getWidth()));
                mTabStrip.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {

            if (mode==1) {
                scrollToTab(position,0);
                for (int i = 0 ; i <mTabStrip.getChildCount();i++){
                    ((TextView)mTabStrip.getChildAt(i)).setSelected(position==i);
                }
                for (int i = 0 ; i<mTabStrip.getChildCount();i++){
                    ((TextView)mTabStrip.getChildAt(i)).setBackgroundResource(R.drawable.bg_tabview_normal);
                }
                ((TextView)mTabStrip.getChildAt(position)).setBackgroundResource(R.drawable.bg_tabview_selected);
            }
            else if (mode==2){
                for (int i = 0 ; i <mTabStrip.getChildCount();i++){
                    ((TextView)mTabStrip.getChildAt(i)).setSelected(position==i);
                }

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void scrollToTab(int position, int posionOffset) {
        View view = mTabStrip.getChildAt(position);
        if (view!=null){
            int transX = view.getLeft() + posionOffset;
            if (position>0 || posionOffset>0){
                transX-=mTitleOffset;
            }
            scrollTo(transX,0);
        }
    }
}
