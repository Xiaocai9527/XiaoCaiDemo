package com.xiaokun.xiusou.demo6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.socks.library.KLog;
import com.yuyh.library.utils.data.safe.MD5;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import static com.yuyh.library.utils.io.IOUtils.streamToString;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public class DownLoadActivity extends AppCompatActivity {

    private String result;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    postData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//        post().url("http://z.01808.cn/api/download/")
//                .addParams("appkey","123456")
//                .addParams("baoming","com.xiaokun.xiusou.demo66").build().execute(new
// StringCallback() {
//
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                KLog.d(response);
//            }
//        });
    }

    private void postData() throws IOException {
        HashMap<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("appkey", "123456");
        paramsMap.put("baoming", "com.xiaokun.xiusou.demo66");
        paramsMap.put("uid", "522414716");

        StringBuilder tempParams = new StringBuilder();
        int pos = 0;
        for (String key : paramsMap.keySet()) {
            if (pos > 0) {
                tempParams.append("&");
            }
            tempParams.append(String.format("%s=%s", key,
                    URLEncoder.encode(paramsMap.get(key), "utf-8")));
            pos++;
        }
        String params = tempParams.toString();
        // 请求的参数转换为byte数组
        byte[] postData = params.getBytes();

        String url = "http://z.01808.cn/api/download/";
        URL serverUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) serverUrl
                .openConnection();
        // 设置连接超时时间
        conn.setConnectTimeout(5 * 1000);
        // 设置从主机读取数据超时
        conn.setReadTimeout(5 * 1000);
        // Post请求必须设置允许输出 默认false
        conn.setDoOutput(true);
        // 设置请求允许输入 默认是true
        conn.setDoInput(true);
        // Post请求不能使用缓存
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        // 必须设置false，否则会自动redirect到Location的地址
        conn.setInstanceFollowRedirects(false);
        conn.addRequestProperty("Accept-Charset", "UTF-8;");
        OutputStream dos = conn.getOutputStream();
        dos.write(postData);
        dos.flush();
        dos.close();
        conn.connect();
        //302跳转
        if (conn.getResponseCode() == 200) {
            result = streamToString(conn.getInputStream());
            if (result.equals("2")) {
                KLog.d("无此用户或用户账号无法使用");
            } else if (result.equals("3")) {
                KLog.d("未查到此软件");
            } else if (result.equals("4")) {
                KLog.d("添加下载记录失败");
            }
        }else {
            String location = conn.getHeaderField("Location");
            KLog.d("跳转地址:");
            serverUrl = new URL(location);
            conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("Referer", location);
            conn.connect();
            KLog.d("跳转地址:" + location);
            KLog.d("获取失败");
        }
    }

}
