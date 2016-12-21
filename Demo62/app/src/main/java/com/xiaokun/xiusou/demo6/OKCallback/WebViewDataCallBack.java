package com.xiaokun.xiusou.demo6.OKCallback;

import com.google.gson.Gson;
import com.xiaokun.xiusou.demo6.Bean.WebViewData;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public abstract class WebViewDataCallBack extends Callback<WebViewData> {

    @Override
    public WebViewData parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        WebViewData webViewData = new Gson().fromJson(string, WebViewData.class);
        return webViewData;
    }
}
