package com.xiaokun.xiusou.demo6.OKCallback;

import android.util.Log;

import com.google.gson.Gson;
import com.xiaokun.xiusou.demo6.Bean.ZakerData;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public abstract class ZakerDataCallBack extends Callback<ZakerData> {

    @Override
    public ZakerData parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        Log.d("xiao", "xiao:" + string);
        ZakerData zakerData = new Gson().fromJson(string, ZakerData.class);
        return zakerData;
    }
}
