package com.xiaokun.xiusou.demo6.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xiaocai on 2016/11/11 0011.
 */

public abstract class LazyFragment extends Fragment {
    /**
     * 标志位，标志已经onCreateView执行完成.目的：为了让onCreateView先执行完，再执行setUserVisibleHint
     * 中的LazyLoad()方法
     */
    public boolean isInit;
    /**
     * 标志位，标志此fragment已经获得数据。目的：避免页面往回滑时，重复加载数据
     */
    boolean isGetData = false;

    public interface onDataLoadedListener {
        void onDataLoaded();
    }

    public static onDataLoadedListener onDataLoadedListener;

    /**
     * fragment数据加载完成的回调
     *
     * @param listener
     */
    public static void setOnDataLoadedListener(onDataLoadedListener listener) {
        onDataLoadedListener = listener;
    }

    private static final String LOG_TAG = "LazyFragment";
    /**
     * 标志位，标志碎片已经可见
     */
    protected boolean isVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        initView(inflater, container);
        if (getUserVisibleHint() && !isGetData) {
            isInit = true;
            lazyLoad();
//            lazyLoad();//这里的lazyLoad纯粹是为了首页加载的，所以说首页数据需要这句代码
            //非首页不满足可见条件所以也执行不了
        } else {
            isInit = true;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void initView(LayoutInflater inflater, ViewGroup container);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            //当碎片可见时调用
            isVisible = true;
            onVisible();
        } else {
            //当碎片不可见时调用
            isVisible = false;
            onInvisible();
        }
    }

    private void onInvisible() {
//        isGetData = false;//这个设置为false时，页面会重新加载
    }

    /**
     * 页面可见时，加载数据
     */
    private void onVisible() {
        lazyLoad();
    }

    public void lazyLoad() {
        //当碎片可见且碎片初始化完成，为什么要确保初始化完成？
        //因为setUserVisibleHint是在onCreateView之前调用的，而LazyFragment中lazyLoad方法会在可见时调用
        //这里就会有一个问题，一般来讲，数据都是加载在控件上的，如果控件都没有进行初始化，那就会造成空指针
        //那什么叫控件初始化呢，那就是findviewbyid方法了。
        if (isVisible && isInit && !isGetData) {
            //三个条件：1.可见；2.是否初始化；3.是否加载过数据
            getData();
            isGetData = true;
        } else {
            return;
        }
    }

    /**
     * 获取数据的方法
     */
    public abstract void getData();
}
