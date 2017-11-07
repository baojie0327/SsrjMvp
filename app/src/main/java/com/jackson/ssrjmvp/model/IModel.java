package com.jackson.ssrjmvp.model; /**
 * IModel  2017-10-30
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.UserLoginBean;
import com.jackson.ssrjmvp.bean.parameter.LoginBody;

/**
 * 所有的ViewModel Interface
 * @author Jackson
 * @version 1.0.0
 * since 2017 10 30
 */
public class IModel {


    /**
     * 登录
     */
    public interface ILoginModel {
        void login(LoginBody loginBody, MyCallBack<UserLoginBean> callBack); //登录
    }

}

