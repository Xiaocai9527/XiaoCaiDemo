package com.xiaokun.xiusou.demo6;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public class RWDemoActivity extends AppCompatActivity {
    String url;
    private String str;//网页源代码
    private StringBuffer html;

    @InjectView(R.id.mWeb)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.inject(this);

        url = "http://mp.weixin.qq.com/s/4-ikPCWFjHXbF9yIRbyeqQ?";
//        url = "http://mp.weixin.qq.com/s?__biz=MzI4ODUxOTMzNQ==&mid=2247483657&idx=1&sn" +
//                "=f0fa523ac4dbfa78ab62c86d22165f86&chksm" +
//                "=ec3c64c1db4bedd7c8bc080d41449dabbcafd3883006ae43a67cc7b1f09aca0cb5f8a6c2d357";

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537" +
                ".11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
        webView.loadUrl(url);
//        final String finalJs = js;
//        webView.setWebViewClient(new WebViewClient() {
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
////                super.onPageStarted(view, url, favicon);
//                Log.d("pengge", "penggebuxin");
//                String js = "var newscript = document.createElement(\"script\");";
//                js += "newscript.src=\"file:///android_asset/jquery-2.1.1.min.js\";";
////                js += "newscript.onload=function(){\n";
////                js += "$(\"img\").each(function(){\n";
////                js += "});\n";
////                js += "};";
//                js += "document.head.appendChild(newscript);";
////                js += "document.getElementById('activity-name').innerHTML='12345';\n";
////                js += "setTimeout('$(\"#activity-name\").html(\"nihao\");',2000);\n";
//                js = "function onStart(){\n";
//                js += "var src=document.getElementsByTagName('img');var t=\"\";\n";
//                js += "for (var i=0;i<src.length;i++){\n";
//                js += "if(src[i].attributes['data-src']){\n";
//                js += "t+=\"0\";\n";
//                js += "}\n";
//                js += "}\n";
//                js += "document.getElementById('activity-name').innerHTML=t;\n";
//                js += "}\n";
//
//                js += "setTimeout(\"window.onload=function(){onStart()};\",1000);\n";
////                js += "window.onload=function(){onStart()}";
//                Log.d("pengge", "penggebuxin");
//                webView.loadUrl("javascript:" + js);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
////                super.onPageFinished(view, url);
//
//
//            }
//        });
//        getOriginCode();//获取源代码


    }

    private void getOriginCode() {
        Thread thread = new Thread(runnable);
        thread.start();
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
                str = str.replaceAll("data-src", "src");
                Log.d("xiao", "RWdemo:" + str);
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
                    webView.loadDataWithBaseURL(null, str, "text/html", "utf-8",
                            null);
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);
//                            webView.
                        }
                    });
                    break;
            }
        }
    };
}
