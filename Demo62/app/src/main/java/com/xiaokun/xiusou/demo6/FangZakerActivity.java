package com.xiaokun.xiusou.demo6;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.Adapter.FangZakerAdapter;
import com.xiaokun.xiusou.demo6.Bean.FangZakerData;
import com.xiaokun.xiusou.demo6.CustomView.CustomRecyclerView;
import com.xiaokun.xiusou.demo6.CustomView.XKSwipeRefreshLayout;
import com.xiaokun.xiusou.demo6.OKCallback.FangZakerDataCallback;
import com.yuyh.library.view.text.ShimmerTextView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.yuyh.library.utils.AsyncExecutor.handler;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class FangZakerActivity extends AppCompatActivity {
    private static final String TAG = "FangZakerActivity";
    @InjectView(R.id.fang_recycler)
    CustomRecyclerView recyclerView;
    @InjectView(R.id.SwipeRefreshLayout)
    XKSwipeRefreshLayout swipeRefreshLayout;
    private FangZakerData data;
    private List<FangZakerData.Data.article> articles;
    boolean isLoading = false;
    private FangZakerAdapter fangZakerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fang_zaker);
        ButterKnife.inject(this);
        getData();
        ShimmerTextView view;

        swipeRefreshLayout.setOnRefreshListener(refreshListener);
    }

    SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout
            .OnRefreshListener() {
        @Override
        public void onRefresh() {
            new Handler() {
            }.postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 1000);
        }
    };

    private void getData() {
        OkHttpUtils.get().url("http://iphone.myzaker.com/zaker/local_tab" +
                ".php?_appid=AndroidPhone&_bsize=480_800&_city=北京&_dev=11&_lat=39" +
                ".913249&_lbs_city=北京&_lbs_province=北京市&_lng=116" +
                ".403625&_mac=34%3A97%3AF6%3AA3%3AA1%3AFB&_mcode=595F9E44&_net=wifi&_nudid" +
                "=c599543459aa651f&_os=4.4" +
                ".2_M351&_os_name=M351&_province=北京市&_udid=864394010349720&_v=7.0.1&_version=7" +
                ".0&city=beijing").build().execute(new FangZakerDataCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(FangZakerData response, int id) {
                data = response;
//                Log.d("TAG", "gcacacacaca:  " + response.getData().get
//                        Gallery());
                setAdapter(response);
                swipeRefreshLayout.setChildView(recyclerView);
            }
        });
    }

    private void setAdapter(FangZakerData response) {
        articles = response.getData().getArticles();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        fangZakerAdapter = new FangZakerAdapter(this, response);
        fangZakerAdapter.setOnItemClickListener(listener);
        recyclerView.setAdapter(fangZakerAdapter);
        KLog.d("recyclerview:" + recyclerView.canScrollVertically(1));
        KLog.d("recyclerview:" + recyclerView.computeVerticalScrollOffset());
        KLog.d("recyclerview:" + (recyclerView.computeVerticalScrollRange() - recyclerView
                .computeVerticalScrollExtent()));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                KLog.d("recyclerview:" + recyclerView.canScrollVertically(1));
                KLog.d("recyclerview:" + recyclerView.computeVerticalScrollOffset());
                KLog.d("recyclerview:" + (recyclerView.computeVerticalScrollRange() - recyclerView
                        .computeVerticalScrollExtent()));
                int lastVisibleItemPosition = linearLayoutManager
                        .findLastVisibleItemPosition();
                if (articles.size() > fangZakerAdapter.num) {
                    if (lastVisibleItemPosition + 1 == fangZakerAdapter.num) {

                        boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                        if (isRefreshing) {//如果正在刷新，滑到底端时，将footerview移除掉
                            fangZakerAdapter.notifyItemRemoved(fangZakerAdapter
                                    .getItemCount());
                            return;
                        }
                        if (!isLoading) {
                            isLoading = true;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
//                                        zakerListAdapter.num += 10;
                                    if (articles.size() > (fangZakerAdapter.num + 10)) {
                                        fangZakerAdapter.num += 10;
                                        Log.d("tag", "num:" + fangZakerAdapter.num);
                                        fangZakerAdapter.notifyDataSetChanged();
                                    } else if (articles.size() < (fangZakerAdapter.num + 10) &&
                                            articles.size() > fangZakerAdapter.num) {
                                        fangZakerAdapter.num = articles.size();
                                        fangZakerAdapter.notifyItemRemoved(fangZakerAdapter
                                                .getItemCount());
                                    } else if (articles.size() == fangZakerAdapter.num) {
                                        fangZakerAdapter.notifyItemRemoved(fangZakerAdapter
                                                .getItemCount());
                                        Toast.makeText(FangZakerActivity.this,
                                                "没有更多数据了",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    isLoading = false;
                                }
                            }, 1000);
                        }
                    }
                } else if (articles.size() == fangZakerAdapter.num) {

                }
                swipeRefreshLayout.setRefreshing(false);
//                progressBar.setVisibility(View.GONE);
//                recyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    FangZakerAdapter.onItemClickListener listener = new FangZakerAdapter.onItemClickListener() {

        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent();
            intent.setClass(FangZakerActivity.this, WebActivity.class);
            intent.putExtra("url", data.getData().getArticles().get(position).getWeburl());
            startActivity(intent);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
