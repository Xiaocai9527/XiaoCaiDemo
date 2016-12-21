package com.xiaokun.xiusou.demo6;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class Intent2Activity extends AppCompatActivity {
    private static final String TAG = "Intent2Activity";
    @InjectView(R.id.web_view)
    WebView webView;
    @InjectView(R.id.btn2)
    Button button;
    @InjectView(R.id.btn3)
    Button button3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        ButterKnife.inject(this);

        webView.loadUrl("file:///android_asset/intentToActivity.html");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = getApplicationContext().getPackageManager()
//                        .getLaunchIntentForPackage("com.xiaokun.xiusou.intent2demo");
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                        .FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );//这种方式会多一个页面重复出现
//                Intent intent = new Intent(Intent.ACTION_MAIN);
//                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                // 这里的packname就是从上面得到的目标apk的包名
                // Intent resolveIntent = packManager.getLaunchIntentForPackage(packname);
                ComponentName cn = new ComponentName("com.xiaokun.xiusou.intent2demo",
                        "com.xiaokun.xiusou.intent2demo.Intent2Activity");
                intent.setComponent(cn);
//                intent.putExtra("article", "1");
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setPackage("com.xiusou.activity");
                i.setAction("android.intent.action.VIEW");
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                        .FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                i.addCategory("android.intent.category.DEFAULT");
                i.addCategory("android.intent.category.BROWSABLE");
                i.setData(Uri.parse("xs://jaq.xiusou.com"));
                i.putExtra("article","1");
                Log.d(TAG,i.toURI());
                startActivity(i);
            }
        });
    }
}
