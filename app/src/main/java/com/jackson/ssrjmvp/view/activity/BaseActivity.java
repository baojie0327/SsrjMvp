package com.jackson.ssrjmvp.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;

public  class BaseActivity extends AppCompatActivity {

    private ImmersionBar mImmersionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_base);
      /*  if (isImmersionBarEnabled()){
            mImmersionBar = ImmersionBar.with(this);
            mImmersionBar.init();  //所有子类都将继承这些相同的属性
        }*/

    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       /* if (mImmersionBar != null) {
            ////必须调用该方法，防止内存泄漏，不调用该方法，
            /// 如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
            mImmersionBar.destroy();
        }*/
    }

}
