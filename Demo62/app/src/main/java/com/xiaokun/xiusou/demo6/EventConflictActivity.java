package com.xiaokun.xiusou.demo6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.xiaokun.xiusou.demo6.Adapter.PicAdapter;
import com.xiaokun.xiusou.demo6.CustomView.CustomViewPager;
import com.xiaokun.xiusou.demo6.CustomView.XKSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class EventConflictActivity extends AppCompatActivity {

    @InjectView(R.id.SwipeRefreshLayout)
    XKSwipeRefreshLayout swipeRefreshLayout;
    @InjectView(R.id.viewpager)
    CustomViewPager viewPager;
     @InjectView(R.id.indicator)
     CircleIndicator circleIndicator;


    List<String> strings;
    String url1 = "http://c.hiphotos.baidu" +
            ".com/image/pic/item/f7246b600c3387448982f948540fd9f9d72aa0bb.jpg";
    String url2 = "http://c.hiphotos.baidu" +
            ".com/image/pic/item/267f9e2f070828382dcc0b20bd99a9014d08f1c5.jpg";
    String url3 = "http://f.hiphotos.baidu" +
            ".com/image/pic/item/32fa828ba61ea8d358824a0d950a304e251f5812.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_confict);
        ButterKnife.inject(this);

        strings = new ArrayList<String>();
        strings.add(url1);
        strings.add(url2);
        strings.add(url3);
//        viewPager.setLooperPic(true);
        viewPager.setAdapter(new PicAdapter(strings, this, 0));
        circleIndicator.setViewPager(viewPager);
    }
}
