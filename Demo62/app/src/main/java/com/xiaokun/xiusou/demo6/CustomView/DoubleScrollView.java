package com.xiaokun.xiusou.demo6.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.xiaokun.xiusou.demo6.Utils.ViewUtil;

/**
 * Created by xiaocai on 2016/11/14.
 */
public class DoubleScrollView extends ScrollView {

    private int mLastTouchX;
    private int mLastTouchY;

    public DoubleScrollView(Context context) {
        super(context);
    }

    public DoubleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoubleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DoubleScrollView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 头布局
     */
    private View mTitle;

    /**
     * 设置scrollview的headview
     *
     * @param view
     */
    public void setupTitleView(View view) {
        mTitle = view;
    }

    /**
     * 除去头布局的contentview
     */
    private ViewGroup mContentView;

    /**
     * 设置scrollview的contentview
     *
     * @param view
     */
    public void setContentView(ViewGroup view) {
        this.mContentView = view;
    }

    /**
     * 可滑动的view，比如listview
     */
    private View scrollableView;

    /**
     * 给当前scrollview中设置可滑动的view，比如listview
     *
     * @param scrollableView
     */
    public void setContentInnerScrollableView(View scrollableView) {
        this.scrollableView = scrollableView;
    }

    private int maxMoveY;
    private int tabHeight;
    private float mFirstY;
    private float mFirstX;
    private Matrix eventMatrix;//Matrix类保存用于转换坐标的3x3矩阵

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mTitle == null || mContentView == null) {
            return super.dispatchTouchEvent(event);
        }

        if (maxMoveY == 0) {
            maxMoveY = mTitle.getMeasuredHeight();//将头布局的高度赋值给maxMoveY
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstY = event.getY();
                mFirstX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (scrollableView != null) {

                    float mCurrentY = event.getY();
                    float mCurrentX = event.getX();

                    float detalX = mCurrentX - mFirstX;
                    float detalY = mCurrentY - mFirstY;

                    if (tabHeight == 0) {
                        //tablayout的高度 = 主布局的高度 - 可滑动的view的高度
                        tabHeight = mContentView.getMeasuredHeight() - scrollableView
                                .getMeasuredHeight();
                    }
                    eventMatrix = new Matrix();
                    eventMatrix.setTranslate(0, -tabHeight);//将矩阵设置为（0,-tabHeight）。

                    boolean isDown = mCurrentY > mFirstY;//下拉
                    boolean isUp = mCurrentY < mFirstY;//上滑
                    if (getScrollY() <= maxMoveY) {
                        return super.dispatchTouchEvent(event);
                    } else if (Math.abs(detalY) > Math.abs(detalX)) {

                        if (isUp) {
                            if (getScrollY() >= maxMoveY) {
                                event.transform(eventMatrix);//将变换矩阵应用于事件中的所有点
                                return scrollableView.dispatchTouchEvent(event);
                            }
                        } else if (isDown) {
                            if (ViewUtil.isScrollToTop(scrollableView)) {//如果可滑动的view没有滑到顶部
                                event.transform(eventMatrix);//将变换矩阵应用于事件中的所有点
                                return scrollableView.dispatchTouchEvent(event);
                            }
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFirstY = ev.getY();
                mFirstX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float mCurrentY = ev.getY();
                float mCurrentX = ev.getX();

                float detalX = mCurrentX - mFirstX;
                float detalY = mCurrentY - mFirstY;
                boolean isDown = mCurrentY > mFirstY;//下拉
                boolean isUp = mCurrentY < mFirstY;//上滑
                if (Math.abs(detalY) > Math.abs(detalX)) {
                    //解决viewpager的左右滑动和scrollview的上下滑动的冲突
                    if (getScrollY() < maxMoveY) {
                        //若头布局还有那么全部竖直滑动事件SC拦截
                        return true;
                    } else {
                        //若头布局已经隐藏
                        if (isDown) {
                            //下拉
                            if (ViewUtil.isScrollToTop(scrollableView)) {
                                //如果recyclerView滑到顶端了，那么SC拦截
                                return true;
                            } else {//如果recyclerView没有滑到顶端，那么SC不拦截
                                return false;
                            }
                        } else {//上滑,头布局隐藏的情况下上滑，SC不拦截
                            return false;
                        }
                    }
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
