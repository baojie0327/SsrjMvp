package com.jackson.ssrjmvp.dagger.component;

import com.jackson.ssrjmvp.dagger.module.LoginModule;
import com.jackson.ssrjmvp.presenter.LoginPresenter;
import com.jackson.ssrjmvp.view.activity.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lenovo on 2017/11/20.
 * 登录模块
 */
@Singleton
@Component(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
    void inject(LoginPresenter loginPresenter);

}
