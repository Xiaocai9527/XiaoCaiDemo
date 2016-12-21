package com.xiaokun.xiusou.demo6.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.czy1121.view.RoundButton;
import com.xiaokun.xiusou.demo6.R;
import com.xiaokun.xiusou.demo6.Utils.ACache;
import com.yuyh.library.AppUtils;
import com.yuyh.library.utils.toast.ToastUtils;
import com.yuyh.library.view.text.EmailAutoCompleteTextView;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class RegisterFragment extends LazyFragment {
    private static final String LOG_TAG = "RegisterFragment";

    /**
     * 标志位，标志已经onCreateView执行完成.目的：为了让onCreateView先执行完，再执行setUserVisibleHint
     * 中的LazyLoad()方法
     */
    private boolean isInit;
    /**
     * 标志位，标志此fragment已经获得数据。目的：避免页面往回滑时，重复加载数据
     */
    boolean isGetData = false;
    private EditText e1;
    private EditText e2;
    private EmailAutoCompleteTextView e3;
    private RoundButton btn;
    private ToastUtils toastUtils;
    ACache aCache;
    private View v;

    public static RegisterFragment newInstance(Bundle args) {
        RegisterFragment fragment = new RegisterFragment();
//        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return v;
    }

    @Override
    protected void initView(LayoutInflater inflater, ViewGroup container) {
        toastUtils = new ToastUtils();
        aCache = ACache.get(AppUtils.getAppContext());
        v = inflater.from(AppUtils.getAppContext()).inflate(R.layout.fragment_register,
                container,
                false);
        e1 = (EditText) v.findViewById(R.id.password);
        e2 = (EditText) v.findViewById(R.id.password1);
        e3 = (EmailAutoCompleteTextView) v.findViewById(R.id.username);
        btn = (RoundButton) v.findViewById(R.id.ok);
    }


    public void getData() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();

                if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2) && !TextUtils.isEmpty(s3)) {
                    if (aCache.getAsString("username") == null) {
                        if (s1.equals(s2)) {
                            aCache.put("password", s1);
                            aCache.put("username", s3);
                            toastUtils.getSingleToast("注册成功", 0).show();
                        } else {
                            toastUtils.getSingleToast("两次输入的密码不一致", 0).show();
                        }
                    } else {
                        if (aCache.getAsString("username").equals(s3)) {
                            toastUtils.getSingleToast("该账号已经被注册了", 0).show();
                        } else {
                            if (s1.equals(s2)) {
                                aCache.put("password", s1);
                                aCache.put("username", s3);
                                toastUtils.getSingleToast("注册成功", 0).show();
                            } else {
                                toastUtils.getSingleToast("两次输入的密码不一致", 0).show();
                            }
                        }
                    }
                } else {
                    toastUtils.getSingleToast("用户名和密码不能为空", 0).show();
                }
            }
        });
    }
}
