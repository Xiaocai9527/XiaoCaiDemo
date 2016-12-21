package com.xiaokun.xiusou.demo6;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.xiaokun.xiusou.demo6.Fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class ViewPagerActivity extends AppCompatActivity implements TestFragment.onDataLoadedListener {
    @InjectView(R.id.pager)
    ViewPager viewPager;
    List<TestFragment> fragments = new ArrayList<TestFragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_viewpager);
        ButterKnife.inject(this);

        Bundle args1 = new Bundle();
        args1.putInt("color", Color.YELLOW);
        args1.putString("url", "http://app.myzaker.com/news/article" +
                ".php?pk=582ac7c99490cbed04000066&f=xsunion");
        TestFragment testFragment1 = TestFragment.newInstance(args1);

        Bundle args2 = new Bundle();
        args2.putInt("color", Color.RED);
        args2.putString("url", "http://app.myzaker.com/news/article" +
                ".php?pk=582ac7929490cbea04000075&f=xsunion");
        TestFragment testFragment2 = TestFragment.newInstance(args2);

        Bundle args3 = new Bundle();
        args3.putInt("color", Color.BLUE);
        args3.putString("url", "http://app.myzaker.com/news/article" +
                ".php?pk=582abbe19490cbbb0400004f&f=xsunion");
        TestFragment testFragment3 = TestFragment.newInstance(args3);

        Bundle args4 = new Bundle();
        args4.putInt("color", Color.GREEN);
        args4.putString("url", "http://app.myzaker.com/news/article" +
                ".php?pk=582aaaba9490cb7d7c000047&f=xsunion");
        TestFragment testFragment4 = TestFragment.newInstance(args4);

        fragments.add(testFragment1);
        fragments.add(testFragment2);
        fragments.add(testFragment3);
        fragments.add(testFragment4);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);//设置viewpager的缓存页面,经验证设置为2即缓存3张页面
        TestFragment.setOnDataLoadedListener(this);
    }

    @Override
    public void onDataLoaded() {
        MyApplication.toastUtils.showToast("数据加载回调成功");
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
