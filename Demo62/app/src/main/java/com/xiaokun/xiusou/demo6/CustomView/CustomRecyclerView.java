package com.xiaokun.xiusou.demo6.CustomView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.socks.library.KLog;
import com.yuyh.library.AppUtils;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class CustomRecyclerView extends RecyclerView {

    private float startY;
    private float startX;
    // 记录viewPager是否拖拽的标记
    private boolean mIsVpDragger;
    private int mTouchSlop;

    public CustomRecyclerView(Context context) {
        super(context);
        init();
    }


    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mTouchSlop = ViewConfiguration.get(AppUtils.getAppContext()).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 记录手指按下的位置
                startY = ev.getY();
                startX = ev.getX();
                // 初始化标记
                mIsVpDragger = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果viewpager正在拖拽中，那么不拦截它的事件，直接return false；
                // 类似一个滑动锁，滑动的过程中不让拦截事件触发
                if (mIsVpDragger) {
                    return false;
                }

                // 获取当前手指位置
                float endY = ev.getY();
                float endX = ev.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                // 如果X轴位移大于Y轴位移，那么将事件RecyclerView不拦截交给子view处理。
                if ( distanceX > distanceY) {
                    mIsVpDragger = true;
                    KLog.i("Y轴位移小于X轴位移");
                    return false;
                }else {
//                    KLog.i("Y轴位移大于X轴位移");
                }
                //如果Y轴位移大于三倍X轴位移，那么将事件交给swipeRefreshLayout处理
//                if (distanceY > 3 * distanceX) {
//                    KLog.i("Y轴位移大于三倍X轴位移");
//                    mIsVpDragger = true;
//                    return super.onInterceptTouchEvent(ev);
//                } else {
//                    mIsVpDragger = true;
//                    KLog.i("没有交给recyclerview");
//                    return false;
//                }
//                break;
            case MotionEvent.ACTION_UP:
                return super.onInterceptTouchEvent(ev);
            case MotionEvent.ACTION_CANCEL:
                // 初始化标记
                mIsVpDragger = false;
                break;
        }



        // 如果是Y轴位移大于X轴，事件交给RecyclerView处理。
        return super.onInterceptTouchEvent(ev);
    }


}
