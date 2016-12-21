package com.xiaokun.xiusou.demo6.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaokun.xiusou.demo6.Bean.ZakerData;
import com.xiaokun.xiusou.demo6.R;
import com.xiaokun.xiusou.demo6.Utils.PicassoUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/10/28 0028.
 */

public class ZakerListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_NO_DATA = 2;
    private List<ZakerData.Data.innerData> list;
    private Context context;
    private onItemClickListener mItemClickListener;
    public int num = 10;

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public ZakerListAdapter(Context contenxt, List<ZakerData.Data.innerData> datas) {
        this.list = datas;
        this.context = contenxt;
    }

    @Override
    public int getItemViewType(int position) {
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_home, parent,
                    false);
            return new MyViewHolder(view);
        } else if (viewType == TYPE_FOOTER){
            View v = LayoutInflater.from(context).inflate(R.layout.item_foot, parent, false);
            return new FootViewHolder(v);
        } else if(viewType == TYPE_NO_DATA){
            View v = LayoutInflater.from(context).inflate(R.layout.item_end, parent, false);
            return new FootViewHolder(v);
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

        }
    }

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
}
