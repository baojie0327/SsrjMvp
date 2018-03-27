package com.jackson.ssrjmvp.presenter; /**
 * DisCountPresenter  2018-02-06
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.ShopBean;
import com.jackson.ssrjmvp.bean.parameter.DisCountBody;
import com.jackson.ssrjmvp.model.DisCountModel;
import com.jackson.ssrjmvp.view.IView;

import io.reactivex.disposables.Disposable;

/**
 * class description here
 *
 * @author Jackson
 * @version 1.0.0
 *          since 2018 02 06
 */
public class DisCountPresenter {

    private IView.IDisCountView mIDisCountView;
    private DisCountModel mDisCountModel;

    /**
     * 构造方法
     * @param iDisCountView
     */
    public DisCountPresenter(IView.IDisCountView iDisCountView) {
        this.mIDisCountView = iDisCountView;
        mDisCountModel = new DisCountModel();
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

        mDisCountModel.getData(disCountBody, new MyCallBack<ShopBean>() {
            @Override
            public void onSuccess(ShopBean response) {
                if (response.getStatus().equals("1")){
                   // LogUtil.d("response=="+response.getMerchantList().size());
                    mIDisCountView.setData(response.getMerchantList());
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

