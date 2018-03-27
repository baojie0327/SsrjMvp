package com.jackson.ssrjmvp.presenter; /**
 * LoginPresenter  2017-10-25
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.UserLoginBean;
import com.jackson.ssrjmvp.bean.parameter.LoginBody;
import com.jackson.ssrjmvp.dagger.component.DaggerLoginComponent;
import com.jackson.ssrjmvp.dagger.module.LoginModule;
import com.jackson.ssrjmvp.model.LoginModel;
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
 *          since 2017 10 25
 */
public class LoginPresenter {

    private IView.ILoginView mILoginView;

    @Inject
    LoginModel mLoginModel;
  //  private LoginModel mLoginModel;

    public LoginPresenter(IView.ILoginView iLoginView) {
        this.mILoginView = iLoginView;
        inject();
      //  mLoginModel = new LoginModel();
    }

    private void inject(){
        DaggerLoginComponent.builder()
                .loginModule(new LoginModule())
                .build()
                .inject(this);
    }

    /**
     * 登录
     */
    public void login() {
      /*  Map<String, String> paraMap =new HashMap<>();
        paraMap.put("userName",mILoginView.getUserName());
        paraMap.put("passWord",mILoginView.getPassWord());*/
        mILoginView.showProgress();

        LoginBody loginBody = new LoginBody(mILoginView.getPassWord(), mILoginView.getUserName());

        mLoginModel.login(loginBody, new MyCallBack<UserLoginBean>() {
            @Override
            public void onSuccess(UserLoginBean userLoginBean) {
                mILoginView.hideProgress();
                if (userLoginBean.getStatus().equals("1")) {
                    mILoginView.toOtherActivity();
                }
                mILoginView.showToast(userLoginBean.getMessage());


            }

            @Override
            public void onError(String header, String message) {
                mILoginView.hideProgress();
                mILoginView.showToast(message);
            }

            @Override
            public void onDispose(Disposable disposable) {
                mILoginView.closeDispose(disposable);
            }
        });


      /*  mLoginModel.login(loginBody, new MyCallBack.OnLoginListener() {
            @Override
            public void loginSuccess(UserLoginBean userLoginBean) {
                mILoginView.showToast(userLoginBean.getMessage());
            }

            @Override
            public void loginFailed(String header, String message) {

            }

            @Override
            public void dispose(Disposable disposable) {
                mILoginView.closeDispose(disposable);
            }
        });*/

    }


    private Map<String, String> createParaMap() {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("userName", mILoginView.getUserName());
        paraMap.put("passWord", mILoginView.getPassWord());
        return paraMap;
    }

}

