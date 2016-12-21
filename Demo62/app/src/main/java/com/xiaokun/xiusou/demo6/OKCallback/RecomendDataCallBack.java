package com.xiaokun.xiusou.demo6.OKCallback;

import com.google.gson.Gson;
import com.xiaokun.xiusou.demo6.Bean.RecommendData;
import com.zhy.http.okhttp.callback.Callback;

import java.util.List;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/20 0020.
 */
public abstract class RecomendDataCallBack extends Callback<List<RecommendData.data>> {

    @Override
    public List<RecommendData.data> parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        RecommendData recommendData = new Gson().fromJson(string, RecommendData.class);
        List<RecommendData.data> mNews = recommendData.getList();
        return mNews;
    }
}
