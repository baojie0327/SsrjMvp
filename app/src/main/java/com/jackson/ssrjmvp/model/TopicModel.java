package com.jackson.ssrjmvp.model; /**
 * DisCountModel  2018-02-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.JsNetworkService;
import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.ShopBean;
import com.jackson.ssrjmvp.bean.parameter.DisCountBody;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 资讯
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 02 06
 */
public class TopicModel implements IModel.ITopicModel {

    private JsNetworkService mJsNetworkService;

    /**
     * 构造方法
     * @param jsNetworkService
     */
    public TopicModel(JsNetworkService jsNetworkService) {
        this.mJsNetworkService=jsNetworkService;
    }

    /**
     * 获取数据
     * @param disCountBody
     * @param callBack
     */
    @Override
    public void getData(DisCountBody disCountBody, final MyCallBack<ShopBean> callBack) {
        mJsNetworkService.getIJsNetworkService()
                .getDisCountData(disCountBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Disposable mDisposable; //用于Activity销毁时停止执行
                        mDisposable = d;
                        callBack.onDispose(mDisposable);
                    }

                    @Override
                    public void onNext(@NonNull ShopBean shopBean) {
                        callBack.onSuccess(shopBean);
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

