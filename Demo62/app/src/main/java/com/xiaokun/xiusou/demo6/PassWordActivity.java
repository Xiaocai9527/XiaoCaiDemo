package com.xiaokun.xiusou.demo6;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xiaokun.xiusou.demo6.Adapter.FragmentAdapter;
import com.xiaokun.xiusou.demo6.Fragment.LazyFragment;
import com.xiaokun.xiusou.demo6.Fragment.LoginFragment;
import com.xiaokun.xiusou.demo6.Fragment.RegisterFragment;
import com.yuyh.library.utils.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by xiaocai on 2016/11/18 0018.
 */

public class PassWordActivity extends AppCompatActivity {

    @InjectView(R.id.group)
    RadioGroup group;
    @InjectView(R.id.login)
    RadioButton login;
    @InjectView(R.id.register)
    RadioButton register;
    @InjectView(R.id.mImage)
    ImageView imageView;
    @InjectView(R.id.mPager)
    ViewPager viewPager;
    List<LazyFragment> testFragments = new ArrayList<LazyFragment>();
    private float x;
    private List<View> allchildren = new ArrayList<View>();

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.inject(this);
        List<View> allChildViews = getAllChildViews();
        for (int i = 0; i < allChildViews.size(); i++) {
            if (allChildViews.get(i) instanceof EditText) {
                CharSequence accessibilityClassName = ((EditText) allChildViews.get(i))
                        .getAccessibilityClassName();
                int id = allChildViews.get(i).getId();

            }
        }

        Bundle args1 = new Bundle();
//        args1.putInt("color", Color.YELLOW);
//        args1.putString("url", "http://app.myzaker.com/news/article" +
//                ".php?pk=582ac7c99490cbed04000066&f=xsunion");
//        TestFragment testFragment1 = TestFragment.newInstance(args1);
        LoginFragment loginFragment = LoginFragment.newInstance(args1);

        Bundle args2 = new Bundle();
//        args2.putInt("color", Color.RED);
//        args2.putString("url", "http://app.myzaker.com/news/article" +
//                ".php?pk=582ac7929490cbea04000075&f=xsunion");
//        TestFragment testFragment2 = TestFragment.newInstance(args2);

        RegisterFragment registerFragment = RegisterFragment.newInstance(args2);

        x = imageView.getTranslationX();
        LogUtils.d("x:" + x);
        testFragments.add(loginFragment);
        testFragments.add(registerFragment);

        viewPager.setOffscreenPageLimit(1);//设置viewpager的缓存页面,经验证设置为2即缓存3张页面
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), testFragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {
                //这里最关键的地方还是positionOffset参数(k)，这个参数表示当前页面向右滑动的百分比

                float imageWidth = imageView.getWidth();
                float Rsize = (float) (15 + 15 * 0.2 * (positionOffset + position));
                float Lsize = (float) (18 - 15 * 0.2 * (positionOffset + position));
                register.setTextSize(Rsize);
                login.setTextSize(Lsize);
                LogUtils.d("positionOffset:" + Rsize);
                imageView.setTranslationX(imageWidth * (position + positionOffset));
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        login.setTextColor(Color.parseColor("#7f7f7f"));
                        register.setTextColor(Color.parseColor("#aaaaaa"));
                        break;

                    case 1:
                        login.setTextColor(Color.parseColor("#aaaaaa"));
                        register.setTextColor(Color.parseColor("#7f7f7f"));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setTranslationX(x);
                viewPager.setCurrentItem(0);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setTranslationX(x + imageView.getWidth());
                viewPager.setCurrentItem(1);
            }
        });
    }

    /**
     * @note 获取该activity所有view
     * @author liuh
     */
    public List<View> getAllChildViews() {
        View view = this.getWindow().getDecorView();
        return getAllChildViews(view);
    }

    private List<View> getAllChildViews(View view) {
        if (view instanceof ViewGroup) {
            Log.d("xiao", "ViewGroup:" + view.toString());
            ViewGroup vp = (ViewGroup) view;
            if (vp.getChildCount() == 0) {
//                allchildren.add(vp);
            } else {
                for (int i = 0; i < vp.getChildCount(); i++) {
                    View viewchild = vp.getChildAt(i);
                    if (viewchild instanceof ViewGroup) {
//                        if (viewchild.toString().contains("ContentFrameLayout") || viewchild
//                                .toString().contains("FitWindowsLinearLayout")) {
//                            return allchildren;
//                        } else {
                        getAllChildViews(viewchild);
//                            allchildren.addAll(getAllChildViews(viewchild));
                        Log.d("xiao", "viewchild:String: " + viewchild.toString());
                        Log.d("xiao", "viewchild:ID: " + viewchild.getId());
                        Log.d("xiao", "viewchild:Count: " + vp.getChildCount());
//                        }
                    } else {
                        if (viewchild.getId() != -1) {
                            allchildren.add(viewchild);
//                            Log.d("xiao", "viewchild:ID: " + viewchild.getId());
                        }
                    }
//                    Log.d("xiao", "viewchild:" + viewchild.getId());
//

                }
                Log.d("xiao", "allchildren:" + allchildren.size());
            }
        }

        return allchildren;
    }

}
