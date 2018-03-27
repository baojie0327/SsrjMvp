package com.jackson.ssrjmvp.view.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jackson.ssrjmvp.R;
import com.jackson.ssrjmvp.dagger.component.DaggerLoginComponent;
import com.jackson.ssrjmvp.dagger.module.LoginModule;
import com.jackson.ssrjmvp.presenter.LoginPresenter;
import com.jackson.ssrjmvp.utils.CommonMethod;
import com.jackson.ssrjmvp.view.IView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity implements IView.ILoginView {


  //  LoginPresenter mLoginPresenter = new LoginPresenter(this);
    @Inject
    LoginPresenter mLoginPresenter;

    @BindView(R.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R.id.tv_head_title)
    TextView mTvHeadTitle;
    @BindView(R.id.tl_username)
    TextInputLayout mTlUsername;
    @BindView(R.id.tl_password)
    TextInputLayout mTlPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.progressbar)
    ProgressBar mProgressbar;

    private Disposable mDisposable; //用于Activity销毁时停止执行



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        inject();
        initView();

    }

    /**
     * Dagger2的inject方法
     */
    private void inject(){
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mTvHeadTitle.setText("登录");
        // 输入账号监听
        mTlUsername.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String number = editable.toString().trim();//输入账号
                String input_passwd = mTlPassword.getEditText().getText().toString();//输入密码
                if (number.length() > 0 && input_passwd.length() >= 6) {
                    mBtnLogin.setBackgroundResource(R.drawable.shape_rectangle_main_color);
                } else {
                    mBtnLogin.setBackgroundResource(R.drawable.shape_button_gray);
                }
            }
        });
        // 输入账号密码
        mTlPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String number = mTlUsername.getEditText().getText().toString();//输入账号
                String input_passwd = editable.toString().trim();//输入密码
                if (number.length() > 0 && input_passwd.length() >= 6) {
                    mBtnLogin.setBackgroundResource(R.drawable.shape_rectangle_main_color);
                } else {
                    mBtnLogin.setBackgroundResource(R.drawable.shape_button_gray);
                }
            }
        });
    }

    /**
     * 登录
     */
    @OnClick(R.id.btn_login)
    public void login() {
        if (TextUtils.isEmpty(getUserName())) {
            mTlUsername.setErrorEnabled(true);
            mTlUsername.setError("账号不能为空");
        } else if (!validatePassword(getPassWord())) {
            mTlPassword.setErrorEnabled(true);
            mTlPassword.setError("密码字数过少");
        } else {
            mLoginPresenter.login();
        }
    }

    //回退
    @OnClick(R.id.ll_back)
    public void back() {
        finish();
    }


    @Override
    public String getUserName() {
        return mTlUsername.getEditText().getText().toString().trim();
    }

    @Override
    public String getPassWord() {
        return mTlPassword.getEditText().getText().toString().trim();
    }

    @Override
    public void showToast(String content) {
        if (!TextUtils.isEmpty(content)) {
            CommonMethod.showToast(LoginActivity.this, content, false);
        }

    }

    /**
     * 关闭流
     * @param disposable
     */
    @Override
    public void closeDispose(Disposable disposable) {
        this.mDisposable = disposable;
    }

    @Override
    public void showProgress() {
        mProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void toOtherActivity() {
        this.finish();
    }

    private boolean validatePassword(String password) {
        return password.length() >= 6;
    }
}
