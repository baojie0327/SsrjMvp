package com.jackson.ssrjmvp.view.activity; /**
 * ViewModelActivity  2017-11-02
 * Copyright (c) 2017 KL Co.Ltd. All right reserved.
 */

import android.os.Bundle;

/**
 * class description here
 * @author Jackson
 * @version 1.0.0
 * since 2017 11 02
 */
abstract class ViewModelActivity extends BaseActivity{

    abstract void initInject();  //inject

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
    }
}

