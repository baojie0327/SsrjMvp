package com.jackson.ssrjmvp.dagger.component;

import com.jackson.ssrjmvp.dagger.module.TopicModule;
import com.jackson.ssrjmvp.presenter.TopicPresenter;
import com.jackson.ssrjmvp.view.fragment.TopicFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Lenovo on 2017/11/20.
 * 资讯模块
 */
@Singleton
@Component(modules = {TopicModule.class})
public interface TopicComponent {

    void inject(TopicFragment topicFragment);
    void inject(TopicPresenter topicPresenter);

}
