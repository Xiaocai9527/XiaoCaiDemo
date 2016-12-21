package com.xiaokun.xiusou.demo6.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiaokun.xiusou.demo6.Bean.FangZakerData;
import com.xiaokun.xiusou.demo6.Utils.PicassoUtil;
import com.xiaokun.xiusou.demo6.WebActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9 0009.
 */

public class PicAdapter extends PagerAdapter {

    private List<FangZakerData.Data.article> list1;
    private List<String> list2;
    private List<FangZakerData.Data.Gallery> list;
    private Context context;

    public PicAdapter(List<FangZakerData.Data.Gallery> gallery, Context context) {
        this.list = gallery;
        Log.d("TAG", "cacacacaca" + list);
        this.context = context;
    }

    public PicAdapter(List<String> url, Context context, int i) {
        this.list2 = url;
        Log.d("TAG", "cacacacaca" + list2);
        this.context = context;
    }

    public PicAdapter(List<FangZakerData.Data.article> articles, Context context, String s) {
        this.list1 = articles;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else if (list2 != null) {
            return list2.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        if (list != null) {
            PicassoUtil.loadImageViewHolder(context, list.get(position).getPromotion_img(),
                    imageView);
        } else {
            PicassoUtil.loadImageViewHolder(context, list2.get(position), imageView);
        }
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, WebActivity.class);
                if (list != null) {
                    Log.d("tag", "url:" + list.get(position).getArticle().getWeburl());
                    intent.putExtra("url", list.get(position).getArticle().getWeburl());
                } else {
                    Toast.makeText(context, "这个是本地的", Toast.LENGTH_SHORT).show();
                }
                context.startActivity(intent);
            }
        });
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
