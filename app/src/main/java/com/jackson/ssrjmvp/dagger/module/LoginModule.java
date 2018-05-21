package com.jackson.ssrjmvp.dagger.module; /**
 * LoginModule  2017-10-25
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.JsNetworkService;
import com.jackson.ssrjmvp.apiservice.RetrofitClient;
import com.jackson.ssrjmvp.model.LoginModel;
import com.jackson.ssrjmvp.presenter.LoginPresenter;
import com.jackson.ssrjmvp.utils.Constant;
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

    public LoginModule(){

    }

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

    @Provides
    @Singleton
    LoginModel provideLoginModel(JsNetworkService jsNetworkService){
        return new LoginModel(jsNetworkService);
    }

    @Provides
    @Singleton
    JsNetworkService provideJsNetworkService(RetrofitClient retrofitClient){
        return new JsNetworkService(retrofitClient);
    }

    @Provides
    @Singleton
    RetrofitClient provideRetrofitClient(){
        return new  RetrofitClient(Constant.baseUrl);
    }

}

