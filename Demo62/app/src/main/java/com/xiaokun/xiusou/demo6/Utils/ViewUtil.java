package com.xiaokun.xiusou.demo6.Utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import com.xiaokun.xiusou.demo6.CustomView.DSRefView;

/**
 * Created by 小菜 on 16/11/16.
 */

public class ViewUtil {
    public static boolean isScrollToTop(View view) {
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            int first = listView.getFirstVisiblePosition();
            if (first == 0) {
                return true;
            } else {
                return false;
            }
        } else if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            return !recyclerView.canScrollVertically(-1);
//            return !android.support.v4.view.ViewCompat
//                    .canScrollVertically(recyclerView, -1);//参数为正数检查下滑，能往下滑动返回true，反之false
        } else if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            return scrollView.getScrollY() == 0;
        } else if (view instanceof DSRefView) {
            DSRefView dsRefView = (DSRefView) view;
            return dsRefView.isReleaseTouch();
        }
        return true;
    }


    public static void scrollToTop(View view) {
        if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            scrollView.smoothScrollTo(0, 0);
        } else if (view instanceof ListView) {
            ListView listView = (ListView) view;
//            listView.smoothScrollToPosition(0);
            listView.setSelection(0);
        }
    }

    public static <T> T findView(View view, Class<T> tClass) {
        if (view.getClass() == tClass) {
            return (T) view;
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View subView = viewGroup.getChildAt(i);
                T find = findView(subView, tClass);
                if (find != null) {
                    return find;
                }
            }
        }
        return null;
    }

    /**
     * RV是否能往下滑动
     *
     * @param recyclerView
     * @return if true表示能往下滑，反之不能
     */
    public static boolean isCanScrollDown(RecyclerView recyclerView) {
        if (recyclerView.computeVerticalScrollOffset() <= 0) {
            return false;
        } else {
            return true;
        }
//        return recyclerView.canScrollVertically(1);//是否能往下滑动
    }
}
