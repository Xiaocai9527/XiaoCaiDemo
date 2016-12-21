package com.xiaokun.xiusou.demo6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaokun.xiusou.demo6.Bean.WebViewData;
import com.xiaokun.xiusou.demo6.FullListView.NestFullListView;
import com.xiaokun.xiusou.demo6.FullListView.NestFullListViewAdapter;
import com.xiaokun.xiusou.demo6.FullListView.NestFullViewHolder;
import com.xiaokun.xiusou.demo6.OKCallback.WebViewDataCallBack;
import com.xiaokun.xiusou.demo6.Utils.ACache;
import com.xiaokun.xiusou.demo6.Utils.PicassoUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/21 0021.
 */
public class MyWebActivity extends AppCompatActivity {

    private static final String TAG = "MyWebActivity";
    @InjectView(R.id.mProgressBar)
    ProgressBar mProgressBar;
    @InjectView(R.id.web_List)
    ListView listView;
    private String content;
    private String title;
    private String urlid;
    private List<WebViewData.recomends> recomends;
    private String id;
    private String url;
    private View headview;
    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private RelativeLayout layout3;
    private RelativeLayout layout4;
    private RelativeLayout layout5;
    private WebView webView;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private TextView title1;
    private TextView title2;
    private TextView title3;
    private TextView title4;
    private TextView title5;
    private TextView content1;
    private TextView content2;
    private TextView content3;
    private TextView content4;
    private TextView content5;
    private View view;
    private LinearLayout headView;
    private NestFullListView nestFullListView;
    private ACache cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.inject(this);

        cache = ACache.get(this);
        setView();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        id = intent.getStringExtra("id");
        Log.d("tag", "hahxiaocai:" + id);
        if (cache.getAsObject(url) != null) {
            doResult((WebViewData) cache.getAsObject(url));
        } else {
            id="223354";
            getDataFromServer(id);
        }


        listView.addHeaderView(view);
        listView.setAdapter(new MyAdapter());
    }

    private void setView() {
        view = View.inflate(this, R.layout.head_view1, null);
        headView = (LinearLayout) view.findViewById(R.id.head_view);
        webView = (WebView) view.findViewById(R.id.webview);
        nestFullListView = (NestFullListView) view.findViewById(R.id.cstFullShowListView);

//        imageView1 = (ImageView) view.findViewById(R.id.image1);
//        imageView2 = (ImageView) view.findViewById(R.id.image2);
//        imageView3 = (ImageView) view.findViewById(R.id.image3);
//        imageView4 = (ImageView) view.findViewById(R.id.image4);
//        imageView5 = (ImageView) view.findViewById(R.id.image5);
//        title1 = (TextView) view.findViewById(R.id.title1);
//        title2 = (TextView) view.findViewById(R.id.title2);
//        title3 = (TextView) view.findViewById(R.id.title3);
//        title4 = (TextView) view.findViewById(R.id.title4);
//        title5 = (TextView) view.findViewById(R.id.title5);
//        content1 = (TextView) view.findViewById(R.id.content1);
//        content2 = (TextView) view.findViewById(R.id.content2);
//        content3 = (TextView) view.findViewById(R.id.content3);
//        content4 = (TextView) view.findViewById(R.id.content4);
//        content5 = (TextView) view.findViewById(R.id.content5);
//        layout1 = (RelativeLayout) view.findViewById(R.id.layout1);
//        layout2 = (RelativeLayout) view.findViewById(R.id.layout2);
//        layout3 = (RelativeLayout) view.findViewById(R.id.layout3);
//        layout4 = (RelativeLayout) view.findViewById(R.id.layout4);
//        layout5 = (RelativeLayout) view.findViewById(R.id.layout5);

//        layout1.setOnClickListener(this);
//        layout2.setOnClickListener(this);
//        layout3.setOnClickListener(this);
//        layout4.setOnClickListener(this);
//        layout5.setOnClickListener(this);
    }

    private void getDataFromServer(String id) {
        OkHttpUtils.post().url("http://z.01808.cn/api/content/").addParams("id", id)
                .addParams("username", "").addParams("key", "").build()
                .execute(new WebViewDataCallBack() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(WebViewData response, int id) {
                        doResult(response);
                    }
                });
    }

    private void doResult(WebViewData response) {
        content = response.getContent();
        title = response.getTitle();
        urlid = response.getUrlid();
        recomends = response.getList();
        Log.d("tag", "xiaocai:" + recomends);
        Log.d("tag", "xiaocai:" + recomends.get(0).getTitle());
        Log.d("tag", "xiaocai:" + recomends.get(0).getJianjie());
        Log.d("tag", "xiaocai:" + recomends.size());
        Log.d("tag", "xiaocai:" + content);
        loadWebview();
//                        loadRecomend();
        cache.put(url, response);

        NestFullListViewAdapter<WebViewData.recomends> nestFullListViewAdapter = new
                NestFullListViewAdapter<WebViewData.recomends>(R.layout
                        .recomend_item, recomends) {
                    @Override
                    public void onBind(final int pos, WebViewData.recomends list,
                                       NestFullViewHolder holder) {
                        holder.setText(R.id.title5, list.getTitle());
                        holder.setText(R.id.content5, list.getJianjie());
                        PicassoUtil.loadImageViewHolder(MyWebActivity.this, list
                                        .getImg(),
                                (ImageView) holder.getView(R.id.image5));
                        holder.getConvertView().setOnClickListener(new View
                                .OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setClass(MyWebActivity.this, MyWebActivity
                                        .class);
                                intent.putExtra("id", recomends.get(pos).getId());
                                intent.putExtra("url", recomends.get(pos).getUrl());
                                intent.putExtra("to", "zaker");
                                startActivity(intent);
                            }
                        });
                    }
                };

        nestFullListView.setAdapter(nestFullListViewAdapter);
    }



    private void loadRecomend() {
        title1.setText(recomends.get(0).getTitle());
        title2.setText(recomends.get(1).getTitle());
        title3.setText(recomends.get(2).getTitle());
        title4.setText(recomends.get(3).getTitle());
        title5.setText(recomends.get(4).getTitle());
        content1.setText(recomends.get(0).getJianjie().replaceAll("\\s*", ""));
        content2.setText(recomends.get(1).getJianjie().replaceAll("\\s*", ""));
        content3.setText(recomends.get(2).getJianjie().replaceAll("\\s*", ""));
        content4.setText(recomends.get(3).getJianjie().replaceAll("\\s*", ""));
        content5.setText(recomends.get(4).getJianjie().replaceAll("\\s*", ""));
        PicassoUtil.loadImageViewHolder(this, recomends.get(0).getImg(),
                imageView1);
        PicassoUtil.loadImageViewHolder(this, recomends.get(1).getImg(),
                imageView2);
        PicassoUtil.loadImageViewHolder(this, recomends.get(2).getImg(),
                imageView3);
        PicassoUtil.loadImageViewHolder(this, recomends.get(3).getImg(),
                imageView4);
        PicassoUtil.loadImageViewHolder(this, recomends.get(4).getImg(),
                imageView5);
    }

    private void loadWebview() {
        String head = "<head><style>img{max-width:100%} "
                + "body{font-family:\"Helvetica\",\"Arial\",sans-serif;"
                + "line-height:1.5;padding:1em 1em;color:#555;}"
                + "</style></head>";
        String titleHtml = "<h2 align=\"center\">" + title + "</h2>";
        content = content.replaceAll("><img", "img align=center><img " +
                "style=\"display:block\"");
        String contentHtml = "<font size=\"4\">" + content + "</font>";
        String data = head + "<body>" + titleHtml + contentHtml
                + "</body>";
        Log.d("tag", "hahahahahha:" + data);
        webView.loadDataWithBaseURL(null, data, "text/html", "utf-8",
                null);
        webView.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                headView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        Intent intent = new Intent();
//        switch (v.getId()) {
//            case R.id.layout1:
//                intent.setClass(MyWebActivity.this, MyWebActivity.class);
//                intent.putExtra("id", recomends.get(0).getId());
//                intent.putExtra("url", recomends.get(0).getUrl());
//                intent.putExtra("to", "zaker");
//                startActivity(intent);
//                break;
//            case R.id.layout2:
//                intent.setClass(MyWebActivity.this, MyWebActivity.class);
//                intent.putExtra("id", recomends.get(1).getId());
//                intent.putExtra("url", recomends.get(1).getUrl());
//                intent.putExtra("to", "zaker");
//                startActivity(intent);
//                break;
//            case R.id.layout3:
//                intent.setClass(MyWebActivity.this, MyWebActivity.class);
//                intent.putExtra("id", recomends.get(2).getId());
//                intent.putExtra("url", recomends.get(2).getUrl());
//                intent.putExtra("to", "zaker");
//                startActivity(intent);
//                break;
//            case R.id.layout4:
//                intent.setClass(MyWebActivity.this, MyWebActivity.class);
//                intent.putExtra("id", recomends.get(3).getId());
//                intent.putExtra("url", recomends.get(3).getUrl());
//                intent.putExtra("to", "zaker");
//                startActivity(intent);
//                break;
//            case R.id.layout5:
//                intent.setClass(MyWebActivity.this, MyWebActivity.class);
//                intent.putExtra("id", recomends.get(4).getId());
//                intent.putExtra("url", recomends.get(4).getUrl());
//                intent.putExtra("to", "zaker");
//                startActivity(intent);
//                break;
//
//        }
//    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
