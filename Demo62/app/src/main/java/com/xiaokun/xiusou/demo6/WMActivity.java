package com.xiaokun.xiusou.demo6;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.czy1121.view.RoundButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/11/15 0015.
 */

public class WMActivity extends AppCompatActivity {

    @InjectView(R.id.xiaokun)
    EditText xiaokun;
    @InjectView(R.id.xiongwei)
    EditText xiongwei;
    @InjectView(R.id.chencheng)
    EditText chencheng;
    @InjectView(R.id.yiyuting)
    EditText yiyuting;
    @InjectView(R.id.fangjing)
    EditText fangjing;
    @InjectView(R.id.shifu)
    EditText shifu;

    @InjectView(R.id.caculator)
    RoundButton caculator;

    @InjectView(R.id.clear)
    RoundButton clear;

    List<Double> doubles = new ArrayList<Double>();
    private double zong;//实付
    boolean flag = true;//标志已经计算过，防止重复计算


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waimai);
        ButterKnife.inject(this);
        final Intent intent = new Intent();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doubles.clear();
                xiaokun.setText(null);
                xiongwei.setText(null);
                chencheng.setText(null);
                yiyuting.setText(null);
                fangjing.setText(null);
                shifu.setText(null);
                flag = true;
            }
        });

        caculator.setOnClickListener(new View.OnClickListener() {
            private double d5;
            private double d4;
            private double d3;
            private double d2;
            private double d1;

            @Override
            public void onClick(View v) {
                if (flag) {
                    if (TextUtils.isEmpty(shifu.getText())) {
                        String string = "不输入价格，老夫也无能为力!";
                        Toast.makeText(WMActivity.this, string, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    zong = Double.parseDouble(shifu.getText().toString());
                    if (!TextUtils.isEmpty(xiaokun.getText())) {
                        String string = xiaokun.getText().toString();
                        d1 = Double.parseDouble(string);
                        doubles.add(d1);
                    }
                    if (!TextUtils.isEmpty(xiongwei.getText())) {
                        String string = xiongwei.getText().toString();
                        d2 = Double.parseDouble(string);
                        doubles.add(d2);
                    }
                    if (!TextUtils.isEmpty(chencheng.getText())) {
                        String string = chencheng.getText().toString();
                        d3 = Double.parseDouble(string);
                        doubles.add(d3);
                    }
                    if (!TextUtils.isEmpty(yiyuting.getText())) {
                        String string = yiyuting.getText().toString();
                        d4 = Double.parseDouble(string);
                        doubles.add(d4);
                    }
                    if (!TextUtils.isEmpty(fangjing.getText())) {
                        String string = fangjing.getText().toString();
                        d5 = Double.parseDouble(string);
                        doubles.add(d5);
                    }
                    Double allD = 0.00;
                    for (Double d : doubles) {
                        allD += d;
                    }
                    if (zong > allD) {
                        doubles.clear();
                        String string = "逗老夫玩呢，实付比菜钱还高，智障吧！";
                        Toast.makeText(WMActivity.this, string, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double yh = (allD - zong) / doubles.size();//每人优惠多少
                    if (!TextUtils.isEmpty(xiaokun.getText())) {
                        if ((d1 - yh) <= 0) {//防止出现负数，当平均优惠比某个人付出的金额大时，设置为0
                            xiaokun.setText(0 + "");
                        } else {
                            xiaokun.setText((d1 - yh) + "");
                        }
                    }
                    if (!TextUtils.isEmpty(xiongwei.getText())) {
                        if ((d2 - yh) <= 0) {
                            xiongwei.setText(0 + "");
                        } else {
                            xiongwei.setText((d2 - yh) + "");
                        }
                    }
                    if (!TextUtils.isEmpty(chencheng.getText())) {
                        if ((d3 - yh) <= 0) {
                            chencheng.setText(0 + "");
                        } else {
                            chencheng.setText((d3 - yh) + "");
                        }
                    }
                    if (!TextUtils.isEmpty(yiyuting.getText())) {
                        if ((d4 - yh) <= 0) {
                            yiyuting.setText(0 + "");
                        } else {
                            yiyuting.setText((d4 - yh) + "");
                        }
                    }
                    if (!TextUtils.isEmpty(fangjing.getText())) {
                        if ((d5 - yh) <= 0) {
                            fangjing.setText(0 + "");
                        } else {
                            fangjing.setText((d5 - yh) + "");
                        }
                    }
                    flag = false;
                } else {
                    String string = "别TM点了，先清空，再重新计算";
                    Toast.makeText(WMActivity.this, string, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
