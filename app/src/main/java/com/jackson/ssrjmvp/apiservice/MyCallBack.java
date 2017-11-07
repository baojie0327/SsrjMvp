package com.jackson.ssrjmvp.apiservice;

import io.reactivex.disposables.Disposable;

/**
 * Created by Lenovo on 2017/10/25.
 */

public interface MyCallBack<T> {
    void onSuccess(T response);         //成功回调
    void onError(String header,String message);   //失败回调
    void onDispose(Disposable disposable);   // 切断上有发送事件
}
