package com.jackson.ssrjmvp.model; /**
 * IModel  2017-10-30
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.bean.HotSHowBean;
import com.jackson.ssrjmvp.bean.ShopBean;
import com.jackson.ssrjmvp.bean.UserLoginBean;
import com.jackson.ssrjmvp.bean.parameter.DisCountBody;
import com.jackson.ssrjmvp.bean.parameter.LoginBody;

import java.util.Map;

import retrofit2.http.QueryMap;

/**
 * 所有的ViewModel Interface
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2017 10 30
 */
public class IModel {


    /**
     * 登录
     */
    public interface ILoginModel {
        void login(LoginBody loginBody, MyCallBack<UserLoginBean> callBack); //登录
    }

    /**
     * 首页数据
     */
    public interface IHomemodel {
        void getData(@QueryMap Map<String, String> paraMap, MyCallBack<HomeBean> callBack);
    }

    /**
     * 获取HotShow数据
     */
    public interface IHotShowModel {
        void getData(@QueryMap Map<String, String> paraMap, MyCallBack<HotSHowBean> callBack);
    }

    /**
     * 获取资讯列表
     */
    public interface ITopicModel {
        void getData(DisCountBody disCountBody, MyCallBack<ShopBean> callBack);
    }

}

