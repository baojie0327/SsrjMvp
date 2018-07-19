package com.jackson.ssrjmvp.dagger.module; /**
 * LoginModule  2017-10-25
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.JsNetworkService;
import com.jackson.ssrjmvp.apiservice.RetrofitClient;
import com.jackson.ssrjmvp.model.TopicModel;
import com.jackson.ssrjmvp.presenter.TopicPresenter;
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
public class TopicModule {

    private IView.ITopicView mITopicView;

    /**
     * 构造方法，在DisCountPresenter导入时是不需要参数的
     */
    public TopicModule(){

    }

    /**
     * 构造方法，在DisCountFragment导入时需要一个IView.IDisCountView对象
     * @param view
     */
    public TopicModule(IView.ITopicView view){
        this.mITopicView=view;
    }

    /**
     * 提供LoginPresenter
     * @param
     * @return
     */
    @Provides
    @Singleton
    TopicPresenter provideTopicPresenter(IView.ITopicView iTopicView){
        return new TopicPresenter(iTopicView);
    }

    @Provides
    @Singleton
    IView.ITopicView provideITopicView(){
        return mITopicView;
    }



    @Provides
    @Singleton
    TopicModel provideTopicModel(JsNetworkService jsNetworkService){
        return new TopicModel(jsNetworkService);
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

