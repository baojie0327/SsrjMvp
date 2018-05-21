package com.jackson.ssrjmvp.dagger.component;

import com.jackson.ssrjmvp.dagger.module.DisCountModule;
import com.jackson.ssrjmvp.presenter.DisCountPresenter;
import com.jackson.ssrjmvp.view.fragment.DisCountFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lenovo on 2017/11/20.
 * Discount
 */
@Singleton
@Component(modules = {DisCountModule.class})
public interface DiscountComponent {

    void inject(DisCountFragment disCountFragment);
    void inject(DisCountPresenter disCountPresenter);

}
