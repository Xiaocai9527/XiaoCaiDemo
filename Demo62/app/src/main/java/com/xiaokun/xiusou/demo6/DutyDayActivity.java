package com.xiaokun.xiusou.demo6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.allen.library.SuperTextView;
import com.xiaokun.xiusou.demo6.Utils.DutyDayTool;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class DutyDayActivity extends AppCompatActivity {
    @InjectView(R.id.super_tv)
    SuperTextView superTextView;
    @InjectView(R.id.super_tv1)
    SuperTextView superTextView1;
    @InjectView(R.id.super_tv2)
    SuperTextView superTextView2;
    @InjectView(R.id.super_tv3)
    SuperTextView superTextView3;
    @InjectView(R.id.super_tv4)
    SuperTextView superTextView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duty_day);
        ButterKnife.inject(this);


//        superTextView.setRightString();
        String weekDay = DutyDayTool.getWeekDay();
        String week = DutyDayTool.getWeek();
        superTextView4.setRightString(weekDay);
        superTextView3.setRightString(DutyDayTool.getDate("yyyy-MM-dd") + "    " + "星期" +
                week);
        if (weekDay.equals("本周双休")) {
            if (week.equals("六")) {
                superTextView1.setRightString("今天休息，哦耶");
                superTextView2.setRightString("明天休息，哦耶");
            } else if (week.equals("天")) {
                superTextView1.setRightString("今天休息，哦耶");
                superTextView2.setRightString(DutyDayTool.getNextDuty());
            } else if (week.equals("五")) {
                superTextView1.setRightString(DutyDayTool.getDuty());
                superTextView2.setRightString("明天休息，哦耶");
            } else {
                superTextView1.setRightString(DutyDayTool.getDuty());
                superTextView2.setRightString(DutyDayTool.getNextDuty());
            }
        } else if (weekDay.equals("本周单休")) {
            if (week.equals("天")) {
                superTextView1.setRightString("今天休息，哦耶");
                superTextView2.setRightString(DutyDayTool.getNextDuty());
            } else if (week.equals("六")) {
                superTextView1.setRightString(DutyDayTool.getDuty());
                superTextView2.setRightString("明天休息，哦耶");
            } else {
                superTextView1.setRightString(DutyDayTool.getDuty());
                superTextView2.setRightString(DutyDayTool.getNextDuty());
            }
        }

    }
}
