package com.jackson.ssrjmvp.view; /**
 * IView  2017-10-30
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.bean.ShopBean;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 存放所有的View Interface
 * @author Jackson
 * @version 1.0.0
 * since 2017 10 30
 */
public class IView {


    /**
     * 登录
     */
    public interface ILoginView {
        String getUserName();
        String getPassWord();
        void showToast(String content);
        void closeDispose(Disposable disposable);
        void showProgress();
        void hideProgress();
        void toOtherActivity();
    }

    /**
     * 获取附近商户列表
     */
    public interface IDisCountView{
        void setData(List<ShopBean.ShopDetails> mDataList);
        void closeDispose(Disposable disposable);
    }


}

