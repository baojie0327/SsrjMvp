package com.jackson.ssrjmvp.dagger.module; /**
 * LoginModule  2017-10-25
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.JsNetworkService;
import com.jackson.ssrjmvp.apiservice.RetrofitClient;
import com.jackson.ssrjmvp.model.DisCountModel;
import com.jackson.ssrjmvp.presenter.DisCountPresenter;
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
public class DisCountModule {

    private IView.IDisCountView discountView;

    /**
     * 构造方法，在DisCountPresenter导入时是不需要参数的
     */
    public DisCountModule(){

    }

    /**
     * 构造方法，在DisCountFragment导入时需要一个IView.IDisCountView对象
     * @param view
     */
    public DisCountModule(IView.IDisCountView view){
        this.discountView=view;
    }

    /**
     * 提供LoginPresenter
     * @param
     * @return
     */
    @Provides
    @Singleton
    DisCountPresenter provideDisCountPresenter(IView.IDisCountView iDiscountView){
        return new DisCountPresenter(iDiscountView);
    }

    @Provides
    @Singleton
    IView.IDisCountView provideIDisCountView(){
        return discountView;
    }



    @Provides
    @Singleton
    DisCountModel provideDisCountModel(JsNetworkService jsNetworkService){
        return new DisCountModel(jsNetworkService);
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

