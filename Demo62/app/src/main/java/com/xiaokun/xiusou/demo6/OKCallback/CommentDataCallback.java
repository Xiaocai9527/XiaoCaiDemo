package com.xiaokun.xiusou.demo6.OKCallback;

import com.google.gson.Gson;
import com.xiaokun.xiusou.demo6.Bean.CommentData;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/27 0027.
 */

public abstract class CommentDataCallback extends Callback<CommentData> {

    @Override
    public CommentData parseNetworkResponse(Response response, int id) throws Exception {
        String string = response.body().string();
        CommentData commentData = new Gson().fromJson(string, CommentData.class);
        return commentData;
    }
}
