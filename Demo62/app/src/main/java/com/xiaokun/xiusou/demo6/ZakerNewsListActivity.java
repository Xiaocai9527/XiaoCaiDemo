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
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaokun.xiusou.demo6.Adapter.ZakerListAdapter;
import com.xiaokun.xiusou.demo6.Bean.ZakerData;
import com.xiaokun.xiusou.demo6.OKCallback.ZakerDataCallBack;
import com.xiaokun.xiusou.demo6.Utils.PicassoUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class ZakerNewsListActivity extends AppCompatActivity {
    private static final String TAG = "ZakerNewsListActivity";
    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;
    @InjectView(R.id.mProgressBar)
    ProgressBar progressBar;
    @InjectView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    boolean isLoading = false;
    private List<ZakerData.Data.innerData> list;
    int num = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaker_list);
        ButterKnife.inject(this);

        getNewsListData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getNewsListData();
                    }
                }, 1000);
//                list.clear();

            }
        });
    }

    Handler handler = new Handler();

    private void getNewsListData() {
        OkHttpUtils.get().url("http://iphone.myzaker.com/zaker/article_telecom" +
                ".php?app_id=660&for=xsunion").build().execute(new ZakerDataCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(ZakerData response, int id) {
                list = response.getData().getList();

                for (int i = list.size() - 1; i >= 0; i--) {
                    if (list.get(i).getThumbnail_pic() == null) {
                        list.remove(i);
                    }
                }
                final LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                        (ZakerNewsListActivity.this);//
                final ZakerListAdapter zakerListAdapter = new ZakerListAdapter(ZakerNewsListActivity
                        .this, list);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(zakerListAdapter);
                zakerListAdapter.setOnItemClickListener(new ZakerListAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("xxxxxx", list.get(position).getUrl());
                        Intent intent = new Intent(ZakerNewsListActivity.this, ZakerNewsActivity
                                .class);
                        intent.putExtra("url", list.get(position).getUrl());
                        intent.putExtra("pk", list.get(position).getPk());
                        intent.putExtra("title", list.get(position).getTitle());
                        startActivity(intent);
                    }
                });
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        int lastVisibleItemPosition = linearLayoutManager
                                .findLastVisibleItemPosition();
                        if (list.size() > zakerListAdapter.num) {
                            if (lastVisibleItemPosition + 1 == zakerListAdapter.num) {

                                boolean isRefreshing = swipeRefreshLayout.isRefreshing();
                                if (isRefreshing) {
                                    zakerListAdapter.notifyItemRemoved(zakerListAdapter
                                            .getItemCount());
                                    return;
                                }
                                if (!isLoading) {
                                    isLoading = true;
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
//                                        zakerListAdapter.num += 10;
                                            if (list.size() > (zakerListAdapter.num + 10)) {
                                                zakerListAdapter.num += 10;
                                                Log.d("tag", "num:" + zakerListAdapter.num);
                                                zakerListAdapter.notifyDataSetChanged();
                                            } else if (list.size() < (zakerListAdapter.num + 10) &&
                                                    list.size() > zakerListAdapter.num) {
                                                zakerListAdapter.num = list.size();
                                                zakerListAdapter.notifyItemRemoved(zakerListAdapter
                                                        .getItemCount());
                                            } else if (list.size() == zakerListAdapter.num) {
                                                zakerListAdapter.notifyItemRemoved(zakerListAdapter
                                                        .getItemCount());
                                                Toast.makeText(ZakerNewsListActivity.this,
                                                        "没有更多数据了",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                            isLoading = false;
                                        }
                                    }, 1000);
                                }
                            }
                        } else if (list.size() == zakerListAdapter.num) {

                        }

                    }

                });

                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

    }


    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            HoldView mHoler;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.item_zaker_list, null);
                mHoler = new HoldView(convertView);
                convertView.setTag(mHoler);
            } else {
                mHoler = (HoldView) convertView.getTag();
            }

            mHoler.title.setText(list.get(position).getTitle());
            mHoler.content.setText(list.get(position).getAuthor_name());
            PicassoUtil.loadImageViewHolder(getApplicationContext(), list.get(position)
                            .getThumbnail_pic(),
                    mHoler.imageView);

            return convertView;
        }

        class HoldView {
            TextView title;
            TextView content;
            ImageView imageView;

            public HoldView(View view) {
                this.title = (TextView) view.findViewById(R.id.title);
                this.content = (TextView) view.findViewById(R.id.content);
                this.imageView = (ImageView) view.findViewById(R.id.list_imageview);
            }
        }
    }
}
