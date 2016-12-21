package com.xiaokun.xiusou.demo6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xiaokun.xiusou.demo6.Adapter.HomeAdapter1;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class FirstActivity extends AppCompatActivity {

    //    @InjectView(R.id.xsUnion)
//    RoundButton xsUnion;
//    @InjectView(R.id.zaker)
//    RoundButton zaker;
//    @InjectView(R.id.duty_day)
//    RoundButton dutyDay;
//    @InjectView(R.id.weather)
//    RoundButton weather;
//    @InjectView(R.id.view_pager)
//    RoundButton view_pager;
//    @InjectView(R.id.WM)
//    RoundButton wm;
//    @InjectView(R.id.pass_word)
//    RoundButton pass_word;
//    @InjectView(R.id.trans)
//    RoundButton trans;
    @InjectView(R.id.first_recyclerView)
    RecyclerView recyclerView;
    String[] strings = {"秀搜新闻", "Zaker新闻", "秀搜值日", "Zaker精选", "懒加载demo", "外卖云神算",
            "登录模块", "16和10互转", "引导帮助案例", "事件冲突案例", "清空缓存", "顶部悬浮案例",
            "自定义View之芝麻信用", "自定义View之转盘", "自定义View之时钟", "自定义View之搜索",
            "app间跳转案例", "html赏析", "下载app测试"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.inject(this);


        final Intent intent = new Intent();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        final HomeAdapter1 adapter = new HomeAdapter1(strings);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HomeAdapter1.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        intent.setClass(FirstActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(FirstActivity.this, ZakerNewsListActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(FirstActivity.this, DutyDayActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(FirstActivity.this, FangZakerActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(FirstActivity.this, ViewPagerActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(FirstActivity.this, WMActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(FirstActivity.this, PassWordActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.setClass(FirstActivity.this, sixteenToTenActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent.setClass(FirstActivity.this, GuideHelperActivity.class);
                        startActivity(intent);
                        break;
                    case 9:
                        intent.setClass(FirstActivity.this, EventConflictActivity.class);
                        startActivity(intent);
                        break;
                    case 10:
                        MyApplication.aCache.clear();
                        MyApplication.toastUtils.showToast("缓存清除成功");
//                        intent.setClass(FirstActivity.this, EventConflictActivity.class);
//                        startActivity(intent);
                        break;
                    case 11:
                        intent.setClass(FirstActivity.this, TopFloatActivity.class);
//                        startActivity(intent);
                        break;
                    case 12:
                        intent.setClass(FirstActivity.this, CustomViewActivity.class);
                        startActivity(intent);
                        break;
                    case 13:
                        intent.setClass(FirstActivity.this, LuckPanActivity.class);
                        startActivity(intent);
                        break;
                    case 14:
                        intent.setClass(FirstActivity.this, SmartTimeActivity.class);
                        startActivity(intent);
                        break;
                    case 15:
                        intent.setClass(FirstActivity.this, SearchViewActivity.class);
                        startActivity(intent);
                        break;
                    case 16:
                        intent.setClass(FirstActivity.this, Intent2Activity.class);
                        startActivity(intent);
                        break;
                    case 17:
                        intent.setClass(FirstActivity.this, HtmlActivity.class);
                        startActivity(intent);
                        break;
                    case 18:
                        intent.setClass(FirstActivity.this, DownLoadActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });


//        xsUnion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent.setClass(FirstActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//        zaker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent.setClass(FirstActivity.this, ZakerNewsListActivity.class);
//                startActivity(intent);
//            }
//        });
//        dutyDay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent.setClass(FirstActivity.this, DutyDayActivity.class);
//                startActivity(intent);
//            }
//        });
//        weather.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent.setClass(FirstActivity.this, FangZakerActivity.class);
//                startActivity(intent);
//            }
//        });
//        view_pager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent.setClass(FirstActivity.this, ViewPagerActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        wm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent.setClass(FirstActivity.this, WMActivity.class);
//                startActivity(intent);
////                pass_word.performClick();
//            }
//        });
//
//        pass_word.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent.setClass(FirstActivity.this, PassWordActivity.class);
//                startActivity(intent);
////                Toast.makeText(FirstActivity.this, "dayin", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        trans.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent.setClass(FirstActivity.this, sixteenToTenActivity.class);
//                startActivity(intent);
//            }
//        });

    }

//    static class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
//
//        public interface onItemClickListener {
//            void onItemClick();
//        }
//
////        private
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
//                    AppUtils.getAppContext()).inflate(R.layout.item_home1, parent,
//                    false));
//            return holder;
//        }
//
//        @Override
//        public void onBindViewHolder(MyViewHolder holder, int position) {
//            holder.tv.setText(strings[position]);
//        }
//
//        @Override
//        public int getItemCount() {
//            return strings.length;
//        }
//
//        class MyViewHolder extends RecyclerView.ViewHolder {
//
//            TextView tv;
//
//            public MyViewHolder(View view) {
//                super(view);
//                tv = (TextView) view.findViewById(R.id.id_num);
//            }
//        }
//    }
}
