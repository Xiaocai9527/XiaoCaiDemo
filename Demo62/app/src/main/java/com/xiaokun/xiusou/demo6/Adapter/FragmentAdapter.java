package com.xiaokun.xiusou.demo6.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaokun.xiusou.demo6.Fragment.LazyFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    List<LazyFragment> list;

    public FragmentAdapter(FragmentManager fm, List<LazyFragment> fragments) {
        super(fm);
        this.list = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
