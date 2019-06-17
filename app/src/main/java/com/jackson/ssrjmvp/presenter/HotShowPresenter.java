package com.jackson.ssrjmvp.presenter; /**
 * HotShowPresenter  2018-07-12
 * Copyright (c) 2018 JS Co.Ltd. All right reserved.
 */

import android.text.TextUtils;

import com.jackson.ssrjmvp.apiservice.MyCallBack;
import com.jackson.ssrjmvp.bean.HotSHowBean;
import com.jackson.ssrjmvp.dagger.component.DaggerHotShowComponent;
import com.jackson.ssrjmvp.dagger.module.HotShowModule;
import com.jackson.ssrjmvp.model.HotShowModel;
import com.jackson.ssrjmvp.utils.Constant;
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
 *          since 2018 07 12
 */
public class HotShowPresenter {

    private IView.IHotShowView mIHotShowView;

    @Inject
    HotShowModel mHotShowModel;

    public HotShowPresenter(IView.IHotShowView iHotShowView) {
        this.mIHotShowView = iHotShowView;
        inject();
    }

    private void inject() {
        DaggerHotShowComponent.builder()
                .hotShowModule(new HotShowModule())
                .build()
                .inject(this);
    }

    /**
     * 通知Model层，获取数据
     * 获取数据成功后，通知View层数据更新
     *
     * @param type  类型 0-刚进入 1-刷新 2-加载
     * @param start 起始位置
     */
    public void getData(final int type, int start) {
        // 参数
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("apikey", "0df993c66c0c636e29ecbb5344252a4a");
        paraMap.put("city", "北京");
        paraMap.put("start", start + "");
        paraMap.put("count", Constant.PAGE_COUNT + "");
        mHotShowModel.getData(paraMap, new MyCallBack<HotSHowBean>() {
            @Override
            public void onSuccess(HotSHowBean response) {
                // 通知View层进行数据更新
                if (TextUtils.isEmpty(response.getCode())) {
                    switch (type) {
                        case 0:
                            mIHotShowView.setData(response.getSubjects());
                            break;
                        case 1:
                            mIHotShowView.onRefresh(response.getSubjects());
                            break;
                        case 2:
                            mIHotShowView.onLoadMore(response.getSubjects());
                            break;
                    }

                }

            }

            @Override
            public void onError(String header, String message) {

            }

            @Override
            public void onDispose(Disposable disposable) {
                mIHotShowView.closeDispose(disposable);
            }
        });
        // 更新参数
        switch (type) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
        }

    }

}

