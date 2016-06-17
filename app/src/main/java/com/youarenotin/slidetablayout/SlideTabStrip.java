package com.youarenotin.slidetablayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 作者：lubo on 6/16 0016 11:15
 * 邮箱：lubo_wen@126.com
 */
public class SlideTabStrip extends LinearLayout {
    private final Paint mSelectedIndicatorPaint;
    private int mSelectedPosition;
    private float mSelectedOffset;

    public SlideTabStrip(Context context) {
        this(context,null);
    }

    public SlideTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        mSelectedIndicatorPaint = new Paint();
    }



    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mSelectedPosition=position;
        mSelectedOffset=positionOffset;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int height = getHeight();
        int childCount = getChildCount();
        if (childCount>0){
            View view = getChildAt(mSelectedPosition);
            int left = view.getLeft();
            int right = view.getRight();

                View nextTitle = getChildAt(mSelectedPosition + 1);

            if (nextTitle==null){
                canvas.drawRect(left,height-((int)3*getResources().getDisplayMetrics().density),right,height,mSelectedIndicatorPaint);
                return ;
            }
                left = (int) (mSelectedOffset * nextTitle.getLeft() +
                        (1.0f - mSelectedOffset) * left);
                right = (int) (mSelectedOffset * nextTitle.getRight() +
                        (1.0f - mSelectedOffset) * right);

                mSelectedIndicatorPaint.setColor(Color.WHITE);
                canvas.drawRect(left,height-((int)3*getResources().getDisplayMetrics().density),right,height,mSelectedIndicatorPaint);
        }

    }

}
