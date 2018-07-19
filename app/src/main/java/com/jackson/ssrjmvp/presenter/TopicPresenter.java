package com.jackson.ssrjmvp.presenter; /**
 * DisCountPresenter  2018-02-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.ShopBean;
import com.jackson.ssrjmvp.bean.parameter.DisCountBody;
import com.jackson.ssrjmvp.dagger.component.DaggerTopicComponent;
import com.jackson.ssrjmvp.dagger.module.TopicModule;
import com.jackson.ssrjmvp.model.TopicModel;
import com.jackson.ssrjmvp.view.IView;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * 资讯
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 02 06
 */
public class TopicPresenter {

    private IView.ITopicView mITopicView;
   // private DisCountModel mDisCountModel;
    @Inject
   TopicModel mTopicModel;

    /**
     * 构造方法
     * @param iTopicView
     */
    public TopicPresenter(IView.ITopicView iTopicView) {
        this.mITopicView = iTopicView;
       //  mDisCountModel = new DisCountModel();
        inject();
    }


    private void inject(){
        DaggerTopicComponent.builder()
                .topicModule(new TopicModule())
                .build()
                .inject(this);
    }

    public void getData(){
        DisCountBody disCountBody=new DisCountBody();
        disCountBody.setCity("北京");
        disCountBody.setPage(0);
        disCountBody.setRowsPerPage(20);
        disCountBody.setMemberID("");
        disCountBody.setNameOrLandMark("");
        disCountBody.setMerchantType("");
        disCountBody.setLatitude("39.933080");
        disCountBody.setLongitude("116.515896");

        mTopicModel.getData(disCountBody, new MyCallBack<ShopBean>() {
            @Override
            public void onSuccess(ShopBean response) {
                if (response.getStatus().equals("1")){
                   // LogUtil.d("response=="+response.getMerchantList().size());
                    mITopicView.setData(response.getMerchantList());
                }
            }

            @Override
            public void onError(String header, String message) {

            }

            @Override
            public void onDispose(Disposable disposable) {

            }
        });
    }

}

