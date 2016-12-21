package com.xiaokun.xiusou.demo6;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.Adapter.HomeAdapter;
import com.xiaokun.xiusou.demo6.Bean.NewsData;
import com.xiaokun.xiusou.demo6.OKCallback.NewsDataCallBack;
import com.xiaokun.xiusou.demo6.Utils.ACache;
import com.xiaokun.xiusou.demo6.Utils.CustomHashMap;
import com.xiaokun.xiusou.demo6.Utils.CustomJSONArray;
import com.xiaokun.xiusou.demo6.Utils.CustomJSONObject;
import com.yuyh.library.utils.toast.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

import static com.xiaokun.xiusou.demo6.R.id.SwipeRefreshLayout;

/**
 * OkHttpUtils+Picasso+ASimpleCache实现图片和文字的缓存效果，可设置缓存存在时间
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(SwipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    ACache mCache;
    ArrayList<NewsData.NewsDetails> list;
    private ToastUtils toastUtils;
    private NewsData asObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mCache = ACache.get(this);
        toastUtils = new ToastUtils();
        initData();
    }

    private void initData() {
        setSupportActionBar(toolbar);
        toolbar.setTitle("秀搜新闻");
        asObject = (NewsData) mCache.getAsObject(TAG);

        if (asObject != null) {
            list = (ArrayList<NewsData.NewsDetails>) asObject.getList();

//            List<NewsData.NewsDetails> response = (List<NewsData.NewsDetails>) mCache.getAsObject
//                    (TAG);
//            toastUtils.getSingleToast(response.get(0).getHashMap().get("xiao") + "", 0).show();
            try {
                KLog.d("hahahaha3 " + mCache.getAsJSONArray("xiao").getJSONObject(0).getInt
                        ("xiong"));
                KLog.d("hahahaha8 " + asObject.getCustomJSONArray().getJSONObject(0).getInt
                        ("xiong"));
                toastUtils.getSingleToast(mCache.getAsJSONArray("xiao").getJSONObject(0).getInt
                        ("xiong") + "", 0)
                        .show();
            } catch (Exception e) {
                e.printStackTrace();
                KLog.d("hahahaha2" + e.toString());
            }

            setAdapter(list);
        } else {
            KLog.d("cahahahaha");
            getNewsData();
        }

//        if (mCache.getAsObject(TAG) == null) {
//
//        } else {
//            Log.d(TAG, TAG + ":" + "HAHAHA");
//
//        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        //这个时候执行刷新逻辑,然后停止刷新
                        getNewsData();

                    }
                }.sendEmptyMessageDelayed(0, 1000);
            }
        });
    }

    private void getNewsData() {
        OkHttpUtils.post().addParams("urlId", "0").addParams("page", "1").url("http://z.01808" +
                ".cn/api/list_a/").build().execute(new NewsDataCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(NewsData response, int id) {
                setAdapter(response.getList());
                list = (ArrayList<NewsData.NewsDetails>) response.getList();
                list.get(0).setNum(0);
                CustomHashMap<String, Integer> hashMap = new CustomHashMap<>();
                hashMap.put("xiao", 10);
                list.get(0).setHashMap(hashMap);
                JSONArray jsonArray = new JSONArray();

                CustomJSONArray customJSONArray = new CustomJSONArray();
                CustomJSONObject jsonObject = new CustomJSONObject();

                try {
                    jsonObject.put("xiong", 11);
                    customJSONArray.put(jsonObject);
                    jsonArray.put(jsonObject);
                    toastUtils.getSingleToast(customJSONArray.getJSONObject(0).getInt("xiong") + "",
                            0).show();
                    response.setCustomJSONArray(customJSONArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                mCache.put("list", list, 30);//缓存时间为30s
//                mCache.put("list",list,ACache.TIME_DAY);

                KLog.d("hahahaha4:" + response.getCustomJSONArray().toString());
                mCache.put(TAG, response);
                mCache.put("xiao", jsonArray);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void setAdapter(final List<NewsData.NewsDetails> response) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity
                .this);
        recyclerView.setLayoutManager(linearLayoutManager);//设置LayoutManager
        final HomeAdapter adapter = new HomeAdapter(MainActivity.this, response);
        recyclerView.setAdapter(adapter);//设置adapter
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.setOnItemClickListener(new HomeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, MyWebActivity.class);
                intent.putExtra("id", response.get(position).getId());
                intent.putExtra("url", response.get(position).getUrl());
                Log.d("buhao", "url:" + response.get(position).getUrl() + "  ,id:" + response.get
                        (position).getId());
                startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {//表示滑到recyclerView的底部

                }
            }
        });

    }
}

