package com.xiaokun.xiusou.demo6.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.czy1121.view.RoundButton;
import com.xiaokun.xiusou.demo6.R;
import com.xiaokun.xiusou.demo6.Utils.ACache;
import com.yuyh.library.AppUtils;
import com.yuyh.library.utils.toast.ToastUtils;
import com.yuyh.library.view.text.EmailAutoCompleteTextView;
import com.yuyh.library.view.text.PasswordEditText;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class LoginFragment extends LazyFragment {

    private static final String LOG_TAG = "LoginFragment";

    ACache aCache;
    private ToastUtils toastUtils;
    private PasswordEditText editText;
    private EmailAutoCompleteTextView username;
    private RoundButton roundButton;
    private View v;

    public static LoginFragment newInstance(Bundle args) {
        LoginFragment fragment = new LoginFragment();
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
        v = inflater.from(AppUtils.getAppContext()).inflate(R.layout.fragment_login,
                container, false);
        editText = (PasswordEditText) v.findViewById(R.id.passEdit);
        username = (EmailAutoCompleteTextView) v.findViewById(R.id.username);
        roundButton = (RoundButton) v.findViewById(R.id.btn_register);
    }

    public void getData() {
        final String s = aCache.getAsString("password");
        final String s1 = aCache.getAsString("username");
        if (TextUtils.isEmpty(s)) {
            toastUtils.getSingleToast("你还未设置密码，请先在注册页面设置密码", 0).show();
        }
        roundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = editText.getText().toString();
                String e1 = username.getText().toString();
                if (TextUtils.isEmpty(e) || TextUtils.isEmpty(e1)) {
                    toastUtils.getSingleToast("用户名和密码不能为空", 0).show();
                } else {
                    if (TextUtils.isEmpty(s)) {
                        toastUtils.getSingleToast("你还未注册，请先注册", 0).show();
                    } else {
                        if (e.equals(s) && e1.equals(s1)) {
                            toastUtils.getSingleToast("登录成功", 0).show();

                        } else {
                            editText.setText("");
                            toastUtils.getSingleToast("用户名或密码错误", 0).show();
                        }
                    }
                }
            }
        });

    }

}
