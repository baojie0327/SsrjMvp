package com.jackson.ssrjmvp.dagger.module; /**
 * LoginModule  2017-10-25
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.JsNetworkService;
import com.jackson.ssrjmvp.apiservice.RetrofitClient;
import com.jackson.ssrjmvp.model.HomeModel;
import com.jackson.ssrjmvp.presenter.HomePresenter;
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
public class HomeModule {

    private IView.IHomeView mIHomeView;

    /**
     * 构造方法，在DisCountPresenter导入时是不需要参数的
     */
    public HomeModule(){

    }

    /**
     * 构造方法，在DisCountFragment导入时需要一个IView.IDisCountView对象
     * @param view
     */
    public HomeModule(IView.IHomeView view){
        this.mIHomeView=view;
    }

    /**
     * 提供HotShowPresenter
     * @param
     * @return
     */
    @Provides
    @Singleton
    HomePresenter provideHomePresenter(IView.IHomeView iHomeView){
        return new HomePresenter(iHomeView);
    }

    @Provides
    @Singleton
    IView.IHomeView provideIHomeView(){
        return mIHomeView;
    }



    @Provides
    @Singleton
    HomeModel provideHomeModel(JsNetworkService jsNetworkService){
        return new HomeModel(jsNetworkService);
    }

    @Provides
    @Singleton
    JsNetworkService provideJsNetworkService(RetrofitClient retrofitClient){
        return new JsNetworkService(retrofitClient);
    }

    @Provides
    @Singleton
    RetrofitClient provideRetrofitClient(){
        return new  RetrofitClient(Constant.baseUrlJD);
    }

}

