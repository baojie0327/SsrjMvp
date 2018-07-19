package com.jackson.ssrjmvp.apiservice; /**
 * JsNetworkService  2017-10-25
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.bean.HotSHowBean;
import com.jackson.ssrjmvp.bean.ShopBean;
import com.jackson.ssrjmvp.bean.UserLoginBean;
import com.jackson.ssrjmvp.bean.parameter.DisCountBody;
import com.jackson.ssrjmvp.bean.parameter.LoginBody;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 网络服务
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2017 10 25
 */
public class JsNetworkService {

    /**
     * 全局变量的持有,已经改成Dagger2了
     */
    //   private static JsNetworkService instance = new JsNetworkService(new RetrofitClient(Constant.baseUrl));

    /**
     * 构造方法私有化
     * 初始化过程会初始化mIJsNetworkService
     *
     * @param retrofitClient
     */
    public JsNetworkService(RetrofitClient retrofitClient) {
        mIJsNetworkService = retrofitClient.create(JsNetworkService.IJsNetworkService.class);
    }


    /**
     * 提供一个全局访问点
     *
     * @return
     */
   /* public static JsNetworkService getInstance() {
        return instance;
    }*/

    //网络请求服务的接口
    public IJsNetworkService mIJsNetworkService;


    public IJsNetworkService getIJsNetworkService() {
        return mIJsNetworkService;
    }

//---------------------------------------------------------------------------------------------------//

    // 所有请求服务的接口
    public interface IJsNetworkService {

        /**
         * 登录
         *
         * @param
         * @return
         */
        @POST("member/login.do")
        //  @FormUrlEncoded
        Observable<UserLoginBean> getLogin(@Body LoginBody loginBody);

        /**
         * 获取首页数据
         * @param paraMap
         * @return
         */
        @POST("home/content")
        @FormUrlEncoded
        Observable<HomeBean> getHomeData(@FieldMap Map<String, String> paraMap);

        /**
         * 获取HotShow数据
         *
         * @param paraMap
         * @return
         */
        @GET("v2/movie/in_theaters")
        Observable<HotSHowBean> getHotShowData(@QueryMap Map<String, String> paraMap);

        /**
         * 获取资讯列表
         * 获取惠买单商户列表
         *
         * @param disCountBody
         * @return
         */
        @POST("member/queryDisCountMerchant.do")
        Observable<ShopBean> getDisCountData(@Body DisCountBody disCountBody);
    }

}

