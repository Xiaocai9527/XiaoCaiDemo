package com.xiaokun.xiusou.demo6.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaokun.xiusou.demo6.R;
import com.yuyh.library.AppUtils;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class HomeAdapter1 extends RecyclerView.Adapter<HomeAdapter1.MyViewHolder> {

    public onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    String[] datas;

    public HomeAdapter1(String[] strings) {
        this.datas = strings;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(AppUtils.getAppContext()).inflate(R.layout.item_home1,
                parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv.setText(datas[position]);
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);
        }
    }
}
