package com.xiaokun.xiusou.demo6;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                try {
                    sleep(1500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            findViewById(R.id.linear).setBackground(getDrawable(R.mipmap.splash));
                        }
                    });
                    sleep(1500);
                    startActivity(new Intent(SplashActivity.this,FirstActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}
