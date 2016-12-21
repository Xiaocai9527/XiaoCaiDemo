package com.xiaokun.xiusou.demo6.CustomView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.socks.library.KLog;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class CustomViewPager extends ViewPager {

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private float startY;
    private float startX;
    private int mTouchSlop;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                startX = event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                // 获取当前手指位置
                float endY = event.getY();
                float endX = event.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                // 如果X轴位移大于Y轴位移，那么将事件交给viewPager处理。
                if (distanceX > mTouchSlop && 3 * distanceX > distanceY) {
                    KLog.i("viewpager拦截");
                    return true;
                }
//                if (distanceX > mTouchSlop && distanceY > 3 * distanceX) {
//
//                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onInterceptTouchEvent(event);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            default:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(event);
    }
}
