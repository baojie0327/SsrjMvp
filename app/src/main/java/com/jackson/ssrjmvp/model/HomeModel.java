package com.jackson.ssrjmvp.model; /**
 * HotShowModel  2018-07-12
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.JsNetworkService;
import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.HomeBean;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.QueryMap;

/**
 * class description here
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 07 12
 */
public class HomeModel implements IModel.IHomemodel {

    private JsNetworkService mJsNetworkService;

    /**
     * 构造方法
     * @param jsNetworkService
     */
    public HomeModel(JsNetworkService jsNetworkService) {
        this.mJsNetworkService = jsNetworkService;
    }

    /**
     * 获取数据
     * @param paraMap
     * @param callBack
     */
    @Override
    public void getData(@QueryMap Map<String, String> paraMap,final MyCallBack<HomeBean> callBack) {
        mJsNetworkService.getIJsNetworkService()
               .getHomeData(paraMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Disposable mDisposable; //用于Activity销毁时停止执行
                        mDisposable = d;
                        callBack.onDispose(mDisposable);
                    }

                    @Override
                    public void onNext(@NonNull HomeBean homeBean) {
                        callBack.onSuccess(homeBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onError("Server Error", "服务器异常，请稍后再试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}

