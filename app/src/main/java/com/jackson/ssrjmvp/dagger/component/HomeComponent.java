package com.jackson.ssrjmvp.dagger.component;

import com.jackson.ssrjmvp.dagger.module.HomeModule;
import com.jackson.ssrjmvp.presenter.HomePresenter;
import com.jackson.ssrjmvp.view.fragment.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lenovo on 2017/11/20.
 * 资讯模块
 */
@Singleton
@Component(modules = {HomeModule.class})
public interface HomeComponent {

    void inject(HomeFragment homeFragment);
    void inject(HomePresenter homePresenter);

}
