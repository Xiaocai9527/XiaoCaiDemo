package com.xiaokun.xiusou.demo6;

import android.app.Application;

import com.socks.library.KLog;
import com.xiaokun.xiusou.demo6.Utils.ACache;
import com.yuyh.library.AppUtils;
import com.yuyh.library.utils.toast.ToastUtils;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class MyApplication extends Application {

    public static ACache aCache;
    public static ToastUtils toastUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
        KLog.init(BuildConfig.LOG_DEBUG, "xiaocaiwudi");
        aCache = ACache.get(this);
        toastUtils = new ToastUtils();
    }
}
