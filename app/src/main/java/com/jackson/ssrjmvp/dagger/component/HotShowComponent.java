package com.jackson.ssrjmvp.dagger.component;

import com.jackson.ssrjmvp.dagger.module.HotShowModule;
import com.jackson.ssrjmvp.presenter.HotShowPresenter;
import com.jackson.ssrjmvp.view.fragment.HotShowFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lenovo on 2017/11/20.
 * 资讯模块
 */
@Singleton
@Component(modules = {HotShowModule.class})
public interface HotShowComponent {

    void inject(HotShowFragment hotShowFragment);
    void inject(HotShowPresenter hotShowPresenter);

}
