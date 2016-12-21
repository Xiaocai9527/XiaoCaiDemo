package com.xiaokun.xiusou.demo6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.shizhefei.guide.GuideHelper;
import com.xiaokun.xiusou.demo6.Utils.DisplayUtils;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class GuideHelperActivity extends AppCompatActivity {

    private View iconView;
    private View citysView;
    private View infoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_guide_helper);

        iconView = findViewById(R.id.icon);
        citysView = findViewById(R.id.citys);
        infoLayout = findViewById(R.id.infoLayout);

        View button = findViewById(R.id.button1);

        button.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            final GuideHelper guideHelper = new GuideHelper(GuideHelperActivity.this);


            View test = guideHelper.inflate(R.layout.custom_view_show);//功能展示图
            guideHelper.addPage(new GuideHelper.TipData(test, Gravity.CENTER));
            //添加到guideHelper中，居中显示,如果不设置boolean，那默认为true，点击任何地方都进入下一页

            //创建tipData1数据图片，编辑头像，位置居于iconView右下即东南方向
            GuideHelper.TipData tipData1 = new GuideHelper.TipData(R.drawable.tip1, Gravity.RIGHT
                    | Gravity.BOTTOM,
                    iconView);
            //将图片竖直向上移动50dp的高度(-代表向上移动，+代表向下移动)
            tipData1.setLocation(0, -DisplayUtils.dipToPix(v.getContext(), 50));
            Toast.makeText(GuideHelperActivity.this, DisplayUtils.dipToPix(v.getContext(), 50) +
                    "", Toast
                    .LENGTH_SHORT).show();
//            tipData1.setLocation(0, 0);
            guideHelper.addPage(tipData1);//将tipData1提示图片添加到guideHelper中

            //创建tipData2图片，选择城市，默认位置居于citysView的正下方
            GuideHelper.TipData tipData2 = new GuideHelper.TipData(R.drawable.tip2, citysView);
            //将tipData2图片添加到guideHelper中
            guideHelper.addPage(tipData2);

            //创建tipData3图片，编辑用户信息，默认位置居于infoLayout的正下方，这是默认设置
            GuideHelper.TipData tipData3 = new GuideHelper.TipData(R.drawable.tip3, infoLayout);
            //创建tipData4图片，下一步，位置位于整体底部的中间
            GuideHelper.TipData tipData4 = new GuideHelper.TipData(R.drawable.next, Gravity
                    .BOTTOM | Gravity
                    .CENTER_HORIZONTAL);
            //将tipData4图片竖直向上移动100dp
            tipData4.setLocation(0, -DisplayUtils.dipToPix(v.getContext(), 100));
//            tipData4.setLocation(0, 0);
            tipData4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guideHelper.nextPage();
                }
            });
            //将tipData3和tipData4一起当成一个页面添加到guideHelper中
            guideHelper.addPage(false, tipData3, tipData4);
            //将tipData1, tipData2, tipData3一起当成一个页面添加到guideHelper中
            guideHelper.addPage(tipData1, tipData2, tipData3);

            //add custom view
            View testView = guideHelper.inflate(R.layout.custom_view_with_close);
            //点击知道了将其dismiss掉
            testView.findViewById(R.id.guide_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    guideHelper.dismiss();
                }
            });
            GuideHelper.TipData tipDataCustom = new GuideHelper.TipData(testView, Gravity.CENTER);
            guideHelper.addPage(false, tipDataCustom);//添加自定义的tipData到guideHelper中

            guideHelper.show(false);//手动播放
//            guideHelper.show(true);//自动播放

        }
    };
}
