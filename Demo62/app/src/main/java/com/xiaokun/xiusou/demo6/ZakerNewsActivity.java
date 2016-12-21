package com.xiaokun.xiusou.demo6;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaokun.xiusou.demo6.Bean.CommentData;
import com.xiaokun.xiusou.demo6.Bean.RecommendData;
import com.xiaokun.xiusou.demo6.Bean.WebViewData;
import com.xiaokun.xiusou.demo6.OKCallback.CommentDataCallback;
import com.xiaokun.xiusou.demo6.OKCallback.RecomendDataCallBack;
import com.xiaokun.xiusou.demo6.Utils.PicassoUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class ZakerNewsActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.mList)
    ListView listView;
    @InjectView(R.id.mProgressBar)
    ProgressBar progressBar;
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
    private String title;
    private View view;
    private StringBuffer html;
    private String mContent;
    //    private String title;
    private String url;
    private String pk;
    private String mTitle;
    private LinearLayout headView;
    private List<RecommendData.data> mResponse;
    private String content;
    private String xstitle;
    private String urlid;
    private List<WebViewData.recomends> recomends;
    private String str;//网页源代码

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaker);
        ButterKnife.inject(this);
        setView();

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        Log.d("xiaokun", "xxxxxxxxxiaokun" + url);
//        url = "http://iphone.myzaker.com/l.php?l=58183fde9490cbfb36000046&for=xsunion";
//        url = "http://mp.weixin.qq.com/s?__biz=MzI4ODUxOTMzNQ==&mid=2247483657&idx=1&sn" +
//                "=f0fa523ac4dbfa78ab62c86d22165f86&chksm" +
//                "=ec3c64c1db4bedd7c8bc080d41449dabbcafd3883006ae43a67cc7b1f09aca0cb5f8a6c2d357#rd";

        pk = intent.getStringExtra("pk");
        mTitle = intent.getStringExtra("title");
        Log.d("tag", "url:" + url + "pk:" + pk);
        getOriginCode();//获取源代码

        getCommentData();


        listView.addHeaderView(view);
        listView.setAdapter(new MyAdapter());
    }

    private void getOriginCode() {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void getCommentData() {
        OkHttpUtils.post().url("http://z.01808.cn/api/chaxunpinglun2/")
                .addParams("urlid", pk).addParams("page", "1").addParams("isweb", "1").build()
                .execute(new CommentDataCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(CommentData response, int id) {

//                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void setView() {
        view = View.inflate(this, R.layout.head_view, null);
        headView = (LinearLayout) view.findViewById(R.id.head_view);
        webView = (WebView) view.findViewById(R.id.webview);
        imageView1 = (ImageView) view.findViewById(R.id.image1);
        imageView2 = (ImageView) view.findViewById(R.id.image2);
        imageView3 = (ImageView) view.findViewById(R.id.image3);
        imageView4 = (ImageView) view.findViewById(R.id.image4);
        imageView5 = (ImageView) view.findViewById(R.id.image5);
        title1 = (TextView) view.findViewById(R.id.title1);
        title2 = (TextView) view.findViewById(R.id.title2);
        title3 = (TextView) view.findViewById(R.id.title3);
        title4 = (TextView) view.findViewById(R.id.title4);
        title5 = (TextView) view.findViewById(R.id.title5);
        content1 = (TextView) view.findViewById(R.id.content1);
        content2 = (TextView) view.findViewById(R.id.content2);
        content3 = (TextView) view.findViewById(R.id.content3);
        content4 = (TextView) view.findViewById(R.id.content4);
        content5 = (TextView) view.findViewById(R.id.content5);
        layout1 = (RelativeLayout) view.findViewById(R.id.layout1);
        layout2 = (RelativeLayout) view.findViewById(R.id.layout2);
        layout3 = (RelativeLayout) view.findViewById(R.id.layout3);
        layout4 = (RelativeLayout) view.findViewById(R.id.layout4);
        layout5 = (RelativeLayout) view.findViewById(R.id.layout5);

        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        layout4.setOnClickListener(this);
        layout5.setOnClickListener(this);

    }

    private void getReComendData(String mTitle, String pk, String url) {
        OkHttpUtils.post().url("http://z.01808.cn/api/contentlist/").addParams
                ("title", mTitle).addParams("url", url)
                .addParams("urlid", pk).build().execute(new RecomendDataCallBack() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(List<RecommendData.data> response, int id) {
                mResponse = response;
                title1.setText(response.get(0).getTitle());
                title2.setText(response.get(1).getTitle());
                title3.setText(response.get(2).getTitle());
                title4.setText(response.get(3).getTitle());
                title5.setText(response.get(4).getTitle());
                content1.setText(response.get(0).getJianjie().replaceAll("\\s*", ""));
                content2.setText(response.get(1).getJianjie().replaceAll("\\s*", ""));
                content3.setText(response.get(2).getJianjie().replaceAll("\\s*", ""));
                content4.setText(response.get(3).getJianjie().replaceAll("\\s*", ""));
                content5.setText(response.get(4).getJianjie().replaceAll("\\s*", ""));
                PicassoUtil.loadImageViewHolder(ZakerNewsActivity.this, response.get(0).getImg(),
                        imageView1);
                PicassoUtil.loadImageViewHolder(ZakerNewsActivity.this, response.get(1).getImg(),
                        imageView2);
                PicassoUtil.loadImageViewHolder(ZakerNewsActivity.this, response.get(2).getImg(),
                        imageView3);
                PicassoUtil.loadImageViewHolder(ZakerNewsActivity.this, response.get(3).getImg(),
                        imageView4);
                PicassoUtil.loadImageViewHolder(ZakerNewsActivity.this, response.get(4).getImg(),
                        imageView5);
                headView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Log.d("xiao", "xiaokun:aaaaaaaa" + response.get(2).getJianjie());
                Log.d("xiao", "xiaokun:aaaaaaaa");
            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                html = new StringBuffer();
                URL mUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) mUrl
                        .openConnection();
                InputStreamReader isr = new InputStreamReader(conn
                        .getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp;
                while ((temp = br.readLine()) != null) {
                    html.append(temp).append("\n");
                }
                br.close();
                isr.close();

                str = html.toString();


                String pattern = "(?<=p>).*(?=</p>)";// 零宽断言
                String pattern2 = "(?<=title>).*(?=</title>)";

                Pattern r = Pattern.compile(pattern);
                Pattern r2 = Pattern.compile(pattern2);
                boolean matches = Pattern.matches(pattern, str);
                Matcher m = r.matcher(str);
                Matcher m2 = r2.matcher(str);
                // System.out.println("xiaokun" + str);
                if (m.find()) {
                    mContent = m.group();
                    Log.d("xiaokun", "xxxxxxxxxiaokun" + mContent);
                }
                if (m2.find()) {
                    title = m2.group();
//                    title.setText(m2.group());
                }
                handler.sendEmptyMessage(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String head;
                    head = "<head><style>img{max-width:100%} p{text-indent: 2em;}"
                            + "body{font-family:\"Helvetica\",\"Arial\",sans-serif;"
                            + "line-height:1.5;padding:1em 1em;color:#555;}"
                            + "</style> <script language=\"javascript\" src = " +
                            "\"file:///android_asset/ak.js\" ></script> </head>";
                    String titleHtml = "<h2 align=\"center\">" + title + "</h2>";
                    mContent = mContent.replaceAll("><img", "img align=center><img " +
                            "style=\"display:block\"");
                    mContent = mContent.replaceAll("data-original", "src");
                    String contentHtml = "<font size=\"4\">" + mContent + "</font>";
                    String data = "<html>" + head
                            + "<body>" + titleHtml + contentHtml
                            + "<script language=\"javascript\">\n$(function() { $(\"img\")" +
                            ".scrollLoading(); });</script>"
                            + "</body>" + "</html>";
                    Log.d("tag", "datashuju:" + data);
                    WebSettings webSettings = webView.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    webView.loadDataWithBaseURL(null, data, "text/html", "utf-8",
                            null);
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);
                            getReComendData(mTitle, pk, url);

                        }
                    });

                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.layout1:
                intent.setClass(ZakerNewsActivity.this, MyWebActivity.class);
                intent.putExtra("id", mResponse.get(0).getId());
                intent.putExtra("url", mResponse.get(0).getUrl());
                intent.putExtra("to", "zaker");
                startActivity(intent);
                break;
            case R.id.layout2:
                intent.setClass(ZakerNewsActivity.this, MyWebActivity.class);
                intent.putExtra("id", mResponse.get(1).getId());
                intent.putExtra("url", mResponse.get(1).getUrl());
                intent.putExtra("to", "zaker");
                startActivity(intent);
                break;
            case R.id.layout3:
                intent.setClass(ZakerNewsActivity.this, MyWebActivity.class);
                intent.putExtra("id", mResponse.get(2).getId());
                intent.putExtra("url", mResponse.get(2).getUrl());
                intent.putExtra("to", "zaker");
                startActivity(intent);
                break;
            case R.id.layout4:
                intent.setClass(ZakerNewsActivity.this, MyWebActivity.class);
                intent.putExtra("id", mResponse.get(3).getId());
                intent.putExtra("url", mResponse.get(3).getUrl());
                intent.putExtra("to", "zaker");
                startActivity(intent);
                break;
            case R.id.layout5:
                intent.setClass(ZakerNewsActivity.this, MyWebActivity.class);
                intent.putExtra("id", mResponse.get(4).getId());
                intent.putExtra("url", mResponse.get(4).getUrl());
                intent.putExtra("to", "zaker");
                startActivity(intent);
                break;

        }
    }

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
