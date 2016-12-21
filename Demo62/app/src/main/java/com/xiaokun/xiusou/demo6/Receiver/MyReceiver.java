package com.xiaokun.xiusou.demo6.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2016/12/9 0009.
 */

/**
 * 利用广播进行跨进程通信
 */
public class MyReceiver extends BroadcastReceiver {

    private final String TAG = this.getClass().getName();

    @Override
    public void onReceive(Context content, Intent intent) {

        if ("xiusou".equals(intent.getAction())) {
            String appkey = intent.getStringExtra("appkey");
            String device_id = intent.getStringExtra("device_id");
            Log.i(TAG, "xiusouintent:" + appkey + ";   " + device_id);
        }
    }
}
