package com.jackson.ssrjmvp.dagger;

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

}
