package com.jackson.ssrjmvp.dagger; /**
 * LoginModule  2017-10-25
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.presenter.LoginPresenter;
import com.jackson.ssrjmvp.view.IView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2017 10 25
 */
@Module
public class LoginModule {

    private IView.ILoginView loginView;

    public  LoginModule(IView.ILoginView view){
        this.loginView=view;
    }

    /**
     * 提供LoginPresenter
     * @param iLoginView
     * @return
     */
    @Provides
    @Singleton
    LoginPresenter provideLoginPresenter(IView.ILoginView iLoginView){
        return new LoginPresenter(iLoginView);
    }

    @Provides
    @Singleton
    IView.ILoginView provideILoginView(){
        return loginView;
    }

}

