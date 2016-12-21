package com.xiaokun.xiusou.demo6.CustomView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class XkRecyclerView extends RecyclerView {
    public XkRecyclerView(Context context) {
        super(context);
        init();
    }

    public XkRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XkRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }
}
