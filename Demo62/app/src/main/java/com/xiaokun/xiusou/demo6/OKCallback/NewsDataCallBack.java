package com.xiaokun.xiusou.demo6.OKCallback;

import com.google.gson.Gson;
import com.xiaokun.xiusou.demo6.Bean.NewsData;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public abstract class NewsDataCallBack extends Callback<NewsData> {

    @Override
    public NewsData parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        NewsData newsData = new Gson().fromJson(string, NewsData.class);


        return newsData;
    }
}
