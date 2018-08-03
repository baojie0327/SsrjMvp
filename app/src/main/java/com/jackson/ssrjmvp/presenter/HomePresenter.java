package com.jackson.ssrjmvp.presenter; /**
 * HotShowPresenter  2018-07-12
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.dagger.component.DaggerHomeComponent;
import com.jackson.ssrjmvp.dagger.module.HomeModule;
import com.jackson.ssrjmvp.model.HomeModel;
import com.jackson.ssrjmvp.view.IView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * class description here
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 12
 */
public class HomePresenter {

    private IView.IHomeView mIHomeView;

    @Inject
    HomeModel mHomeModel;

    public HomePresenter(IView.IHomeView iHomeView) {
        this.mIHomeView = iHomeView;
        inject();
    }

    /**
     * inject HomeModule
     */
    private void inject() {
        DaggerHomeComponent.builder()
                .homeModule(new HomeModule())
                .build()
                .inject(this);
    }

    /**
     * 通知Model层，获取数据
     * 获取数据成功后，通知View层数据更新
     *
     * @param
     * @param
     */
    public void getData(final int type) {
        // 参数
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("phoneNumber", "18310083556");
        paraMap.put("clientType", "android");
        paraMap.put("osVersion", "8.1.0");
        paraMap.put("model", "MI%208&clientVersion=2.4.0&networkType=wifi&uuid=869071035544817");
        paraMap.put("brand", "Xiaomi");
        paraMap.put("lon", "116.5157");
        paraMap.put("lat", "39.933067");

        mHomeModel.getData(paraMap, new MyCallBack<HomeBean>() {
            @Override
            public void onSuccess(HomeBean response) {
                // 通知View层进行数据更新
                if (response.getCode().equals("1")) {
                    switch (type){
                        case 0:
                            mIHomeView.setData(response.getData());
                            break;
                        case 1:
                            mIHomeView.onRefresh(response.getData());
                            break;
                        case 2:

                            break;
                    }
                }
            }

            @Override
            public void onError(String header, String message) {

            }

            @Override
            public void onDispose(Disposable disposable) {
                mIHomeView.closeDispose(disposable);
            }
        });

    }


}

