package com.xiaokun.xiusou.demo6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class HtmlActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        webView.loadUrl("file:///android_asset/HtmlDemo.html");
        setContentView(webView);
    }
}
