package com.jackson.ssrjmvp.view; /**
 * IView  2017-10-30
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.bean.HomeBean;
import com.jackson.ssrjmvp.bean.HotSHowBean;
import com.jackson.ssrjmvp.bean.ShopBean;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 存放所有的View Interface
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2017 10 30
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
     * View--Home
     */
    public interface IHomeView {
        void setData(List<HomeBean.DataBean> mDataList);  // 设置主体数据
        void onRefresh(List<HomeBean.DataBean> mDataList);  // refresh
        void closeDispose(Disposable disposable);    //  关闭流
    }

    /**
     * View--HotShow
     */
    public interface IHotShowView {
        void setData(List<HotSHowBean.SubjectsBean> mDataList);  // 网络请求成功后设置数据

        void onRefresh(List<HotSHowBean.SubjectsBean> mDataList);  // refresh

        void onLoadMore(List<HotSHowBean.SubjectsBean> mDataList);  // load more

        void closeDispose(Disposable disposable);    //  关闭流
    }

    /**
     * 获取资讯
     */
    public interface ITopicView {
        void setData(List<ShopBean.ShopDetails> mDataList);

        void closeDispose(Disposable disposable);
    }


}

