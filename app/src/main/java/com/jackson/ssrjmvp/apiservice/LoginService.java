package com.jackson.ssrjmvp.apiservice;

import com.jackson.ssrjmvp.bean.UserLoginBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lenovo on 2017/10/27.
 */

public interface LoginService {

    /**
     * 登录
     * @param paraMap
     * @return
     */
    @POST("member/login.do")
    @FormUrlEncoded
    Call<UserLoginBean> getLogin(@FieldMap Map<String,String> paraMap);
}
