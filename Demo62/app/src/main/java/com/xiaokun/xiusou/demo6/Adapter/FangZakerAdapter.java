package com.xiaokun.xiusou.demo6.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.hejunlin.superindicatorlibray.CircleIndicator;
import com.hejunlin.superindicatorlibray.LoopViewPager;
import com.xiaokun.xiusou.demo6.Bean.FangZakerData;
import com.xiaokun.xiusou.demo6.R;
import com.xiaokun.xiusou.demo6.Utils.ACache;
import com.xiaokun.xiusou.demo6.Utils.PicassoUtil;
import com.xiaokun.xiusou.demo6.WebActivity;
import com.yuyh.library.utils.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class FangZakerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_NO_DATA = 2;
    private static final int TYPE_HEADER = 3;
    private FangZakerData response;
    //    private List<ZakerData.Data.innerData> list;
    private Context context;
    private onItemClickListener mItemClickListener;
    public int num = 10;
    private List<FangZakerData.Data.article> list;


    public FangZakerAdapter(Context context, FangZakerData response) {
        this.response = response;
        this.list = response.getData().getArticles();
        this.context = context;
    }

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mItemClickListener = listener;
    }

//    public FangZakerAdapter(Context contenxt, List<ZakerData.Data.innerData> datas) {
//        this.list = datas;
//        this.context = contenxt;
//    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            if (list.size() > num) {
                if (position + 1 == getItemCount()) {
                    return TYPE_FOOTER;
                } else {
                    return TYPE_ITEM;
                }
            } else {
                if (position + 1 == getItemCount()) {
                    return TYPE_NO_DATA;
                } else {
                    return TYPE_ITEM;
                }
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent,
                    false);
            return new MyViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_foot, parent, false);
            return new FootViewHolder(v);
        } else if (viewType == TYPE_NO_DATA) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_end, parent, false);
            return new FootViewHolder(v);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(context).inflate(R.layout.head_fang_zaker, parent, false);
            return new HeadViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).title.setText(list.get(position).getTitle());
//            ((MyViewHolder) holder).content.setText(list.get(position).getAuthor_name());
            PicassoUtil.loadImageViewHolder(context, list.get(position).getThumbnail_pic(), (
                    (MyViewHolder) holder).imageView);
            if (mItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });
            }
        } else if (holder instanceof FootViewHolder) {

        } else if (holder instanceof HeadViewHolder) {
            PicAdapter picAdapter;
            final Intent intent = new Intent();
            if (response.getData().getGallery() != null) {

                Log.d("", "aaaaaaaaaa:" + response.getData().getColumn_menu().getList().get
                        (position).getWeb().getUrl());

                picAdapter = new PicAdapter(response.getData().getGallery(), context);
                ((HeadViewHolder) holder).viewPager.setAdapter(picAdapter);
                ((HeadViewHolder) holder).viewPager.setLooperPic(true);
                ((HeadViewHolder) holder).viewPager.setVisibility(View.VISIBLE);
                ((HeadViewHolder) holder).viewPager.setVisibility(View.VISIBLE);
                ((HeadViewHolder) holder).viewPager.addOnPageChangeListener(new ViewPager
                        .OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int
                            positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
//                        adsNum(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                ((HeadViewHolder) holder).indicator.setViewPager(((HeadViewHolder) holder)
                        .viewPager);
                ((HeadViewHolder) holder).txt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.setClass(context, WebActivity.class);
                        intent.putExtra("url", response.getData().getColumn_menu().getList().get
                                (position).getWeb().getUrl());
                        context.startActivity(intent);
                    }
                });
//                ((HeadViewHolder) holder).txt1.setLeftIcon();
            } else {
                strings = new ArrayList<String>();
                strings.add(url1);
                strings.add(url2);
                strings.add(url3);
                picAdapter = new PicAdapter(strings, context, 0);
                ((HeadViewHolder) holder).viewPager.setVisibility(View.VISIBLE);
                ((HeadViewHolder) holder).indicator.setVisibility(View.VISIBLE);
                ((HeadViewHolder) holder).viewPager.setAdapter(picAdapter);
                ((HeadViewHolder) holder).viewPager.setLooperPic(true);
                ((HeadViewHolder) holder).indicator.setViewPager(((HeadViewHolder) holder)
                        .viewPager);

                Log.d("", "aaaaaaaaaa:" + response.getData().getColumn_menu().getList().get
                        (position).getWeb().getUrl());
                ((HeadViewHolder) holder).viewPager.addOnPageChangeListener(new ViewPager
                        .OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int
                            positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
//                        adsNum(position);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                ((HeadViewHolder) holder).viewPager.setCurrentItem(0);
                adsNum(0);
                ((HeadViewHolder) holder).txt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent.setClass(context, WebActivity.class);
                        intent.putExtra("url", response.getData().getColumn_menu().getList().get
                                (position).getWeb().getUrl());
                        context.startActivity(intent);
                    }
                });
            }

        }
    }

    int num1 = 0;
    int num2 = 0;
    int num3 = 0;

    private void adsNum(int position) {
        ToastUtils toastUtils = new ToastUtils();
        ACache cache = ACache.get(context);
        List<FangZakerData.Data.Gallery> gallery = response.getData().getGallery();
        switch (position) {
            case 0:
                num1++;
                String pk1 = strings.get(position);
                cache.put(pk1, num1 + "");
                toastUtils.getSingleToast(num1 + "", 0).show();
                break;
            case 1:
                num2++;
                String pk2 = strings.get(position);
                cache.put(pk2, num2 + "");
                toastUtils.getSingleToast(num2 + "", 0).show();
                break;
            case 2:
                num3++;
                String pk3 = strings.get(position);
                cache.put(pk3, num3 + "");
                toastUtils.getSingleToast(num3 + "", 0).show();
                break;
        }


    }

    List<String> strings;
    String url1 = "http://c.hiphotos.baidu" +
            ".com/image/pic/item/f7246b600c3387448982f948540fd9f9d72aa0bb.jpg";
    String url2 = "http://c.hiphotos.baidu" +
            ".com/image/pic/item/267f9e2f070828382dcc0b20bd99a9014d08f1c5.jpg";
    String url3 = "http://f.hiphotos.baidu" +
            ".com/image/pic/item/32fa828ba61ea8d358824a0d950a304e251f5812.jpg";

    @Override
    public int getItemCount() {
        return num;
    }

    class MyViewHolder extends ViewHolder {

        private ImageView imageView;
        private TextView title;
//        private TextView content;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            title = (TextView) itemView.findViewById(R.id.id_num);
//            content = (TextView) itemView.findViewById(R.id.content);
        }
    }

    class FootViewHolder extends ViewHolder {

        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class HeadViewHolder extends ViewHolder {

//        private onPageSelectListener onPageSelectListener;
//
//        public interface onPageSelectListener {
//            void onPageSelect(int position);
//        }
//
//        public void setOnPageSelectListener(onPageSelectListener listener) {
//            this.onPageSelectListener = listener;
//        }

        private final LoopViewPager viewPager;
//        private final CustomViewPager viewPager;
        private final CircleIndicator indicator;
        private final SuperTextView txt1;
        private final SuperTextView txt2;
        private final SuperTextView txt3;

        public HeadViewHolder(View itemView) {
            super(itemView);
            viewPager = (LoopViewPager) itemView.findViewById(R.id.viewpager);
//            viewPager = (CustomViewPager) itemView.findViewById(R.id.viewpager);
            indicator = (CircleIndicator) itemView.findViewById(R.id.indicator);
            txt1 = (SuperTextView) itemView.findViewById(R.id.txt1);
            txt2 = (SuperTextView) itemView.findViewById(R.id.txt2);
            txt3 = (SuperTextView) itemView.findViewById(R.id.txt3);
//            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int
//                        positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    onPageSelectListener.onPageSelect(position);
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
        }
    }
}
