package com.xiaokun.xiusou.demo6;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yuyh.library.utils.toast.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class sixteenToTenActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.btn)
    Button btn;
    @InjectView(R.id.text_ten)
    TextView textView;
    @InjectView(R.id.edit_text)
    EditText editText;
    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.text_ten1)
    TextView textView1;
    @InjectView(R.id.edit_text1)
    EditText editText1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixteen_ten);
        ButterKnife.inject(this);
        btn.setOnClickListener(this);
        btn1.setOnClickListener(this);
        editText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                String s = editText.getText().toString();
                String[] strings = s.split(",");
                if (TextUtils.isEmpty(s)) {
                    return;
                } else {
                    StringBuffer string = new StringBuffer();
                    string.append("#");
                    ToastUtils toastUtils = new ToastUtils();

                    for (int i = 0; i < strings.length; i++) {
                        String ten = Integer.toHexString(Integer.parseInt(strings[i]));//10转16
                        string.append(ten);
                    }

                    textView.setText(string);
                    if (strings.length == 3) {
                        int i = Color.parseColor(string.toString());
                        if (i == -1) {
                            toastUtils.getSingleToast("未知的颜色", 0).show();
                        }
                        textView.setTextColor(i);
                    }
                }
                break;
            case R.id.btn1:
                String s1 = editText1.getText().toString();
                String s2 = "#";
                String[] strings1 = s1.split(",");
                if (TextUtils.isEmpty(s1)) {
                    return;
                } else {
                    StringBuffer string = new StringBuffer();
                    for (int i = 0; i < strings1.length; i++) {
                        int i1 = Integer.parseInt(strings1[i], 16);//16转10
                        string.append(i1);
                        s2 += strings1[i];
                    }
                    textView1.setText(string);
                    textView1.setTextColor(Color.parseColor(s2));
                }
                break;
        }
    }
}
