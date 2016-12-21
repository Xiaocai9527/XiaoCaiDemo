package com.xiaokun.xiusou.demo6.Utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/31 0031.
 */

public class DutyDayTool {

    public static Date date = new Date();
    //    public static long testTime = 25 * 24 * 60 * 60 * 1000l;
    public static long testTime = 0;
    public static long time = date.getTime();
    private static long period = 1477755099332l;

    public static String getDuty() {

        long time1 = time + testTime;
//        1477843607875 表示10月31号凌晨0点几分的毫秒数   1477845030931  1477737068106  1477755099332
        long day = (time1 - period) / 1000 / 60 / 60 / 24;
        long num = day / 7;//几个礼拜
        long yu = day % 7;
        Log.d("11ca", "libai:" + num);
        Log.d("11ca", "tianshu:" + day);
        Log.d("11ca", "time1:" + (time1 - period));
        Log.d("11ca", "time1:" + time1);
        Log.d("11ca", "time:" + time);
        Log.d("11ca", "testTime:" + testTime);
        Log.d("11ca", "testTime:" + (time1 - time));
        long allDay = 0;
        if (num == 0) {
            if (day < 6) {
                allDay = day;
            } else {
                allDay = 5;
            }
        } else if (num % 2 == 0 && num != 0) {
            //因为num为双数的话，即表明双休和单休各一半，所以allDay=总天数-双休周末-单休周末
            allDay = day - num - num / 2;//获取双周所有天数
        } else {
            //因为10.31是周一且为双休，表明如果num为奇数，那么双休的次数应该为(num+1)/2，单休次数为(num-1)/2
            allDay = day - (num + 1) / 2 * 2 - (num - 1) / 2;//获取单周所有天数
        }
        return strings[(int) ((allDay - 1) % 5)];
    }

    public static String getNextDuty() {
//        Date date = new Date();
//        long time = date.getTime();
//        1477843607875
        long time2 = time + testTime;
        long day = (time2 - period) / (1000 * 60 * 60 * 24);
        long num = day / 7;//几个礼拜
        long yu = day % 7;
        Log.d("22ca", "wobuxin:" + num);
        Log.d("2ca", "111wobuxin:" + day);
        long allDay = 0;
        if (num == 0) {
            if (day < 6) {
                allDay = day;
            } else {
                allDay = 5;
            }
        } else if (num % 2 == 0 && num != 0) {
            //因为num为双数的话，即表明双周和单周各一半，所以allDay=总天数-
            allDay = day - num - num / 2;//获取双周所有天数
        } else {
            allDay = day - (num + 1) / 2 * 2 - (num - 1) / 2;//获取单周所有天数
        }
        return strings[(int) (allDay % 5)];
    }

    public static String getWeekDay() {
//        Date date = new Date();
//        long time = date.getTime();
//        1477843607875
        long time1 = time + testTime;
        long day = (time1 - period) / (1000 * 60 * 60 * 24) - 1;
        long num = day / 7;//几个礼拜

        Log.d("ca", "wobuxin:" + num);
        Log.d("ca", "111wobuxin:" + day);

        long yu = day % 7;
        long allDay = 0;
        if (num % 2 == 0) {
            allDay = day - num - num / 2;//获取双周所有天数
            return "本周双休";
        } else {
            allDay = day - (num + 1) / 2 * 2 - (num - 1) / 2;//获取单周所有天数
            return "本周单休";
        }
    }

    public static String getDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat(str);
        Date curDate = new Date(System.currentTimeMillis() + testTime);
        String date = formatter.format(curDate);
        return date;
    }

    public static String getWeek() {

        String Week = "";
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(System.currentTimeMillis() + testTime);
            String date1 = formatter.format(curDate);
            c.setTime(formatter.parse(date1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            Week += "天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            Week += "六";
        }

        return Week;
    }

    public static String[] strings = {" 万鹏, 刘晓洁, 程峰, 覃梦",
            "况石勇, 杨昭, 潘晓丽", "熊维, 胡佩, 肖坤, 陈永帅", "易雨亭, 王京, 王利文", "李晓倩, 方静, 董静, 陈成"};

    /**
     * @param y
     * @param m
     * @param d
     * @return
     */
    public static int getWeek(int y, int m, int d) {
        int W = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
        return W + 1;
    }
}

