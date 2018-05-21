package com.jackson.ssrjmvp.model; /**
 * LoginModel  2017-10-25
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.JsNetworkService;
import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.UserLoginBean;
import com.jackson.ssrjmvp.bean.parameter.LoginBody;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 登录
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2017 10 25
 */
public class LoginModel implements IModel.ILoginModel {

    private JsNetworkService mJsNetworkService;

    /**
     * 构造方法
     *
     * @param
     */
    public LoginModel(JsNetworkService jsNetworkService) {
        this.mJsNetworkService = jsNetworkService;
    }

    @Override
    public void login(LoginBody loginBody, final MyCallBack<UserLoginBean> callBack) {
        mJsNetworkService.getIJsNetworkService()
                .getLogin(loginBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserLoginBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Disposable mDisposable; //用于Activity销毁时停止执行
                        mDisposable = d;
                        callBack.onDispose(mDisposable);
                    }

                    @Override
                    public void onNext(@NonNull UserLoginBean userLoginBean) {
                        callBack.onSuccess(userLoginBean);
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



   /*   @Override
    public void login(LoginBody loginBody, final MyCallBack.OnLoginListener onLoginListener) {

        //   Call<UserLoginBean> call = mJsNetworkService.mIJsNetworkService.getLogin(loginBody);
        JsNetworkService.IJsNetworkService mIJsNetworkService = mJsNetworkService.getIJsNetworkService();

        mIJsNetworkService.getLogin(loginBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserLoginBean>() {
                    private Disposable mDisposable; //用于Activity销毁时停止执行

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mDisposable = d;
                        onLoginListener.dispose(mDisposable);

                    }

                    @Override
                    public void onNext(@NonNull UserLoginBean userLoginBean) {
                        onLoginListener.loginSuccess(userLoginBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        onLoginListener.loginFailed("Server Error", "服务器异常，请稍后再试");
                    }

                    @Override
                    public void onComplete() {

                    }
                });



     *//*   call.enqueue(new Callback<UserLoginBean>() {
            @Override
            public void onResponse(Call<UserLoginBean> call, Response<UserLoginBean> response) {
                if (response.isSuccessful()) {
                    UserLoginBean userLoginBean=response.body();
                //    if (userLoginBean.getStatus().equals("1")){
                        onLoginListener.loginSuccess(userLoginBean);
                 //   }

                } else {
                    onLoginListener.loginFailed("Server Error", "服务器异常，请稍后再试");
                }
            }

            @Override
            public void onFailure(Call<UserLoginBean> call, Throwable t) {
                onLoginListener.loginFailed("Android Application Error", "服务器异常，请稍后再试");
            }
        });*//*


    }*/
}

