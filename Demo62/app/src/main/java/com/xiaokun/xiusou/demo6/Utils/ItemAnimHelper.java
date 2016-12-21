package com.xiaokun.xiusou.demo6.Utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.xiaokun.xiusou.demo6.R;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public class ItemAnimHelper {
    private int mLastPosition = -1;

    public void showItemAnim(final View view, final int position) {
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.item_bottom_in);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    view.setAlpha(1);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            view.startAnimation(animation);
            mLastPosition = position;
        }
    }
}
