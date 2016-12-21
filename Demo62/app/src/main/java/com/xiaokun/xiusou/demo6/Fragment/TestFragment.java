package com.xiaokun.xiusou.demo6.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaokun.xiusou.demo6.R;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.ImageFixCallback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class TestFragment extends LazyFragment {

    private static final String LOG_TAG = "TestFragment";
    public ImageView imageView;
    private TextView textView;
    private String content;
    private String title;
    private String str;
    private String mContent;
    private String data;
    private StringBuffer html;
    private String url;
    private View view;

    public static TestFragment newInstance(Bundle args) {
        TestFragment fragment = new TestFragment();
//        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container) {
        Bundle arguments = getArguments();
        int color = arguments.getInt("color");
        url = arguments.getString("url");
        Log.d(LOG_TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_lazy, container, false);
        imageView = (ImageView) view.findViewById(R.id.image_view);
        textView = (TextView) view.findViewById(R.id.text);
        imageView.setBackgroundColor(color);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    RichText.fromHtml(data).autoFix(false).placeHolder(R.drawable.color_trans)
                            .fix(new ImageFixCallback
                                    () {

                                @Override
                                public void onFix(ImageHolder holder, boolean imageReady) {
                                    if (holder.getImageType() != ImageHolder.ImageType.GIF) {
                                        holder.setAutoFix(true);
                                    } else {
                                        holder.setHeight(200);
                                        holder.setWidth(200);
                                    }
                                }
                            }).into(textView);
                    onDataLoadedListener.onDataLoaded();
                    imageView.setBackgroundResource(R.drawable.color_blue);
                    isGetData = true;//这个地方设置获取数据的标志位为true
                    break;
            }
        }
    };

    public void getData() {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            url = "http://app.myzaker.com/news/article.php?pk=582ac7c99490cbed04000066&f=xsunion";
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
                Log.d("xiaokun", "xxxxxxxxxiaokun" + str);

                String pattern = "(?<=p>).*(?=</p>)";// 零宽断言
                String pattern2 = "(?<=title>).*(?=</title>)";
//
                Pattern r = Pattern.compile(pattern);
                Pattern r2 = Pattern.compile(pattern2);
                boolean matches = Pattern.matches(pattern, str);
                Matcher m = r.matcher(str);
                Matcher m2 = r2.matcher(str);
                if (m.find()) {
                    mContent = m.group();
                    Log.d("xiaokun", "xxxxxxxxxiaokun" + mContent);
                }
                if (m2.find()) {
                    title = m2.group();
//                    title.setText(m2.group());
                }
                mContent = mContent.replaceAll("><img", "img align=center><img " +
                        "style=\"display:block\"");
                mContent = mContent.replaceAll("http://zkres.myzaker" +
                        ".com/static/wap/images/logo32x32.png", "");
                mContent = mContent.replaceAll("data-original", "src");

                data = "<h2 >" + title + "</h2>" + mContent;
//                data = "<h2 >1 分钟看奥巴马执政 8 年来容貌变迁</h2>";
                Log.d("xiaokun", "xxxxxxxxxiaokun" + data);
                handler.sendEmptyMessage(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}


