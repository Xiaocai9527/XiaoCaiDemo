package com.xiaokun.xiusou.demo6.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/10 0010.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收安装广播
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            String packageName = intent.getDataString();

            Log.d("xiao", "安装了:" + packageName + "包名的程序");
            if (packageName.contains("com.xiaokun.xiusou.demo66")){
                //这里可以加一次安装数
                Log.d("xiao1", "安装了:" + packageName + "包名的程序");
                Toast.makeText(context,packageName+"被安装了",Toast.LENGTH_SHORT).show();
            }
        }
        //接收卸载广播
//        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
//            String packageName = intent.getDataString();
//            Log.d("xiao", "卸载了:" + packageName + "包名的程序");
//            if (packageName.contains("xiusou")){
//                Toast.makeText(context,"秀搜被卸载了",Toast.LENGTH_SHORT).show();
//            }
//        }
    }
}
