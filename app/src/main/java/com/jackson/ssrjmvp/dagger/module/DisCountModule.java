package com.jackson.ssrjmvp.dagger.module; /**
 * LoginModule  2017-10-25
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import com.jackson.ssrjmvp.presenter.DisCountPresenter;
import com.jackson.ssrjmvp.view.IView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2017 10 25
 */
@Module
public class DisCountModule {

    private IView.IDisCountView discountView;

    public DisCountModule(IView.IDisCountView view){
        this.discountView=view;
    }

    /**
     * 提供LoginPresenter
     * @param
     * @return
     */
    @Provides
    @Singleton
    DisCountPresenter provideDisCountPresenter(IView.IDisCountView iDiscountView){
        return new DisCountPresenter(iDiscountView);
    }

    @Provides
    @Singleton
    IView.IDisCountView provideIDisCountView(){
        return discountView;
    }

}

